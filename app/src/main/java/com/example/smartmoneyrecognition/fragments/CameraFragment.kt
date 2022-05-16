package com.example.smartmoneyrecognition.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.databinding.FragmentCameraBinding
import com.example.smartmoneyrecognition.fragments.camera_steps.CameraStep1
import com.example.smartmoneyrecognition.fragments.camera_steps.CameraStep2
import com.example.smartmoneyrecognition.fragments.camera_steps.CameraStep3


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

        val firstFragment = CameraStep1()

        setCurrentFragment(firstFragment)

        CameraStep2()
        CameraStep3()
    }

    private fun setCurrentFragment(fragment: Fragment)=
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.flCameraFragment, fragment)
            commit()
        }
}