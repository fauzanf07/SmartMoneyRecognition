package com.example.smartmoneyrecognition.fragments.camera_steps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.databinding.FragmentCameraStep2Binding

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
    }

    private fun setCurrentFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flCameraFragment, fragment)
            commit()
        }
    }

}