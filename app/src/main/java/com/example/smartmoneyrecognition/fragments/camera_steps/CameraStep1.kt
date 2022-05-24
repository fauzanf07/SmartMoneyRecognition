package com.example.smartmoneyrecognition.fragments.camera_steps

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.smartmoneyrecognition.MainActivity
import com.example.smartmoneyrecognition.MainActivity.Companion.CAMERA_X_RESULT
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.activity.CameraActivity
import com.example.smartmoneyrecognition.databinding.FragmentCameraStep1Binding
import java.io.File

class CameraStep1 : Fragment() {
    private var _binding: FragmentCameraStep1Binding?= null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraStep1Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nextFragment = CameraStep2()

        binding.nextStep2.setOnClickListener {
            setCurrentFragment(nextFragment)
        }
        binding.takePicture.setOnClickListener{
            startCameraX()
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
        intent.putExtra("step","cameraStep1")
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            val result = BitmapFactory.decodeFile(myFile.path)

            when(it?.data?.getStringExtra("cameraStep") as String){
                "cameraStep1"->{
                    MainActivity.resultImg1 = result
                }
                "cameraStep2"->{
                    MainActivity.resultImg2 = result
                }
                "cameraStep3"->{
                    MainActivity.resultImg3 = result
                }
            }
            binding.img1.setImageBitmap(result)
        }
    }

    override fun onResume() {
        super.onResume()
        if(MainActivity.resultImg1!=null){
            binding.img1.setImageBitmap(MainActivity.resultImg1)
        }
    }

}