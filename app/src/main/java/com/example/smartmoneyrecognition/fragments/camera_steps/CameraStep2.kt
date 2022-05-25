package com.example.smartmoneyrecognition.fragments.camera_steps

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.smartmoneyrecognition.MainActivity
import com.example.smartmoneyrecognition.MainActivity.Companion.resultImage
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.activity.CameraActivity
import com.example.smartmoneyrecognition.databinding.FragmentCameraStep2Binding
import com.example.smartmoneyrecognition.getIMGSize
import com.example.smartmoneyrecognition.uriToFile
import java.io.File

class CameraStep2 : Fragment() {
    private var _binding: FragmentCameraStep2Binding?= null
    private val  binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraStep2Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val beforeFragment = CameraStep1()
        val nextFragment = CameraStep3()

        binding.before.setOnClickListener {
            setCurrentFragment(beforeFragment)
        }

        binding.nextStep3.setOnClickListener {
            setCurrentFragment(nextFragment)
        }

        binding.takePicture.setOnClickListener{
            startCameraX()
        }
        binding.gallery.setOnClickListener{
            startGallery()
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flCameraFragment, fragment)
            commit()
        }
    }

    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        intent.putExtra("step","cameraStep2")
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
                resultImage.set(1,result)
                binding.img2.setImageBitmap(result)
            }else{
                Toast.makeText(requireActivity(),"Gambar harus berukuran 1:1",Toast.LENGTH_SHORT).show()
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

            resultImage.set(1,result)
            binding.img2.setImageBitmap(result)
        }
    }

    override fun onResume() {
        super.onResume()
        if(resultImage.get(1)!=null){
            binding.img2.setImageBitmap(resultImage.get(1))
        }

    }

}