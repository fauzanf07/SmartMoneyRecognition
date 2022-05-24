package com.example.smartmoneyrecognition.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.smartmoneyrecognition.adapter.RvUangIndonesiaAdapter
import com.example.smartmoneyrecognition.databinding.ActivityUangIndonesiaBinding
import com.example.smartmoneyrecognition.model.JenisUangIndonesiaViewModel

class UangIndonesiaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUangIndonesiaBinding
    private lateinit var viewModel: JenisUangIndonesiaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUangIndonesiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this).get(JenisUangIndonesiaViewModel::class.java)

        val getList = viewModel.getRvLists()

        val rvAdapter = RvUangIndonesiaAdapter(getList)
        binding.rvJenisUangIndonesia.adapter = rvAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}