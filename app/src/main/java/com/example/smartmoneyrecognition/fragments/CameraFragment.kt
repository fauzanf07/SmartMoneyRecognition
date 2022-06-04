package com.example.smartmoneyrecognition.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.smartmoneyrecognition.MainActivity
import com.example.smartmoneyrecognition.activity.AnalisisActivity
import com.example.smartmoneyrecognition.activity.CameraActivity
import com.example.smartmoneyrecognition.databinding.FragmentCameraBinding
import com.example.smartmoneyrecognition.getIMGSize
import com.example.smartmoneyrecognition.ml.Model1Finish
import com.example.smartmoneyrecognition.uriToFile
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions
import com.google.firebase.ml.common.modeldownload.FirebaseModelManager
import com.google.firebase.ml.custom.*
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import java.io.File
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.BufferedReader
import java.io.InputStreamReader


class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBinding?= null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.analyze.setOnClickListener{
            if(MainActivity.resultImage.get(0)!=null){
                analyzeTflite(MainActivity.resultImage.get(0)!!)
            }else{
                Toast.makeText(requireActivity(),"Insert dulu gambar", Toast.LENGTH_SHORT).show()
            }
        }
        binding.takePicture.setOnClickListener{
            startCameraX()
        }

        binding.gallery.setOnClickListener{
            startGallery()
        }
    }

    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        intent.putExtra("step","cameraStep3")
        launcherIntentCameraX.launch(intent)
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireActivity())
            val size = getIMGSize(myFile)
            val result = BitmapFactory.decodeFile(myFile.path)

            if(size.get(0) == size.get(1)){
                MainActivity.resultImage.set(0,result)
                binding.img.setImageBitmap(result)
            }else{
                Toast.makeText(requireActivity(),"Gambar harus berukuran 1:1", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == MainActivity.CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            val result = BitmapFactory.decodeFile(myFile.path)
            MainActivity.resultImage.set(0,result)
            binding.img.setImageBitmap(result)
        }
    }

    override fun onResume() {
        super.onResume()
        if(MainActivity.resultImage.get(0)!=null){
            binding.img.setImageBitmap(MainActivity.resultImage.get(0))
        }

    }

    private fun analyze(image: Bitmap){
        val remoteModel = FirebaseCustomRemoteModel.Builder("smr_1_model").build()
        val conditions = FirebaseModelDownloadConditions.Builder()
            .requireWifi()
            .build()
        FirebaseModelManager.getInstance().download(remoteModel, conditions)
            .addOnCompleteListener {
                // Success.
            }
        val localModel = FirebaseCustomLocalModel.Builder()
            .setAssetFilePath("model1_finish_assets.tflite")
            .build()

        val options = FirebaseModelInterpreterOptions.Builder(localModel).build()
        val interpreter = FirebaseModelInterpreter.getInstance(options)

        FirebaseModelManager.getInstance().download(remoteModel, conditions)
            .addOnCompleteListener {
            }

        FirebaseModelManager.getInstance().isModelDownloaded(remoteModel)
            .addOnSuccessListener { isDownloaded ->
                val options =
                    if (isDownloaded) {
                        FirebaseModelInterpreterOptions.Builder(remoteModel).build()
                    } else {
                        FirebaseModelInterpreterOptions.Builder(localModel).build()
                    }
                val interpreter = FirebaseModelInterpreter.getInstance(options)
            }

        val inputOutputOptions = FirebaseModelInputOutputOptions.Builder()
            .setInputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, 224, 224, 3))
            .setOutputFormat(0, FirebaseModelDataType.FLOAT32, intArrayOf(1, 4))
            .build()

        val bitmap = Bitmap.createScaledBitmap(image, 224, 224, true)

        val batchNum = 0
        val input = Array(1) { Array(224) { Array(224) { FloatArray(3) } } }
        for (x in 0..223) {
            for (y in 0..223) {
                val pixel = bitmap.getPixel(x, y)
                // Normalize channel values to [-1.0, 1.0]. This requirement varies by
                // model. For example, some models might require values to be normalized
                // to the range [0.0, 1.0] instead.
                input[batchNum][x][y][0] = (Color.red(pixel) - 127) / 255.0f
                input[batchNum][x][y][1] = (Color.green(pixel) - 127) / 255.0f
                input[batchNum][x][y][2] = (Color.blue(pixel) - 127) / 255.0f
            }
        }

        val inputs = FirebaseModelInputs.Builder()
            .add(input) // add() as many input arrays as your model requires
            .build()
            interpreter?.run(inputs, inputOutputOptions)
            ?.addOnSuccessListener { result ->
                val output = result.getOutput<Array<FloatArray>>(0)
                val probabilities = output[0]
                Log.i("MLKit", "jhjhjhjj")
                val reader = BufferedReader(
                    InputStreamReader(activity?.assets?.open("labels.txt")))
                for (i in probabilities.indices) {
                    val label = reader.readLine()
                    Log.i("MLKit", String.format("%s: %1.4f", label, probabilities[i]))
                }
            }
            ?.addOnFailureListener { e ->
            }


    }

    private fun analyzeTflite(image : Bitmap){
        val fileName = "labels.txt"
        val inputString = activity?.application?.assets?.open(fileName)?.bufferedReader().use {
            it?.readText()
        }
        val townList = inputString?.split("\n")

        var resize: Bitmap = Bitmap.createScaledBitmap(image,224,224,true)

        val model = Model1Finish.newInstance(requireActivity())

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)

        var tImage = TensorImage(DataType.FLOAT32)
        tImage.load(resize)
        var byteBuffer = tImage.buffer
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        var max  = getMax(outputFeature0.floatArray)


        val intent = Intent(requireContext(), AnalisisActivity::class.java)
        intent.putExtra("index",max)
        intent.putExtra("probability",outputFeature0.floatArray[max])
        intent.putExtra("image",image)
        startActivity(intent)

        Toast.makeText(requireContext(),"${townList?.get(0)} : ${outputFeature0.floatArray[0]}\n" +
                "${townList?.get(1)} : ${outputFeature0.floatArray[1]}\n" +
                "${townList?.get(2)} : ${outputFeature0.floatArray[2]}\n" +
                "${townList?.get(3)} : ${outputFeature0.floatArray[3]}\n",Toast.LENGTH_SHORT).show()
        model.close()
    }

    private fun getMax(arr: FloatArray): Int{
        var idx = 0
        var min = 0.0f

        for(i in 0..3){
            if(arr[i]>min){
                idx = i
                min = arr[i]
            }
        }
        return idx
    }
}