package com.example.smartmoneyrecognition.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.databinding.ActivityMainBinding
import com.example.smartmoneyrecognition.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTipsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsBinding.inflate(layoutInflater)
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