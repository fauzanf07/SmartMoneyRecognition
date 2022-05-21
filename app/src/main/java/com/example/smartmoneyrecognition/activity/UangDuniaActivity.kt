package com.example.smartmoneyrecognition.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.adapter.RvMataUangAdapter
import com.example.smartmoneyrecognition.databinding.ActivityUangDuniaBinding
import com.example.smartmoneyrecognition.model.MataUangDuniaViewModel

class UangDuniaActivity : AppCompatActivity() {
    private var _binding: ActivityUangDuniaBinding?= null
    private val  binding get() = _binding!!
    private lateinit var viewModel: MataUangDuniaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uang_dunia)

        viewModel = ViewModelProvider(this).get(MataUangDuniaViewModel::class.java)

        val getList = viewModel.getRvMataUangLists()

        val rvAdapter = RvMataUangAdapter(getList)
        binding.rvMataUangDunia.adapter = rvAdapter
    }
}