package com.example.smartmoneyrecognition.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.adapter.RvUangIndonesiaAdapter
import com.example.smartmoneyrecognition.databinding.ActivityUangIndonesiaBinding
import com.example.smartmoneyrecognition.model.JenisUangIndonesiaViewModel

class UangIndonesiaActivity : AppCompatActivity() {
    private var _binding: ActivityUangIndonesiaBinding?= null
    private val  binding get() = _binding!!
    private lateinit var viewModel: JenisUangIndonesiaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uang_indonesia)

        viewModel = ViewModelProvider(this).get(JenisUangIndonesiaViewModel::class.java)

        val getList = viewModel.getRvLists()

        val rvAdapter = RvUangIndonesiaAdapter(getList)
        binding.rvJenisUangIndonesia.adapter = rvAdapter
    }
}