package com.example.smartmoneyrecognition.fragments.camera_steps

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.smartmoneyrecognition.MainActivity
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.activity.AnalisisActivity
import com.example.smartmoneyrecognition.activity.CameraActivity
import com.example.smartmoneyrecognition.databinding.FragmentCameraStep3Binding
import java.io.File


class CameraStep3 : Fragment() {

    private var _binding: FragmentCameraStep3Binding?= null
    private val  binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCameraStep3Binding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val beforeFragment = CameraStep2()

        binding.before.setOnClickListener {
            setCurrentFragment(beforeFragment)
        }
        binding.analyze.setOnClickListener{
            val intent = Intent(requireContext(),AnalisisActivity::class.java)
            startActivity(intent)
        }
        binding.takePicture.setOnClickListener{
            startCameraX()
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flCameraFragment,fragment)
            commit()
        }
    }

    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        intent.putExtra("step","cameraStep3")
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == MainActivity.CAMERA_X_RESULT) {
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
            binding.img3.setImageBitmap(result)
        }
    }

    override fun onResume() {
        super.onResume()
        if(MainActivity.resultImg3!=null){
            binding.img3.setImageBitmap(MainActivity.resultImg3)
        }
    }

}