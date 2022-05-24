package com.example.smartmoneyrecognition.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.databinding.ActivityAnalisisBinding
import com.example.smartmoneyrecognition.databinding.ActivityTipsBinding

class AnalisisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnalisisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalisisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}