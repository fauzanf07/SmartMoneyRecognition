package com.example.smartmoneyrecognition.activity

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.databinding.ActivityAnalisisBinding

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

        val idx = intent.getIntExtra("index",0)
        var prob = intent.getFloatExtra("probability",0f)
        val bitmap = intent.getParcelableExtra("image") as Bitmap?
        val label = ArrayList<String>()
        label.add("Rp. 50000 Asli")
        label.add("Rp. 100000 Asli")
        label.add("Rp. 50000 Palsu")
        label.add("Rp. 100000 Palsu")

        binding.ivAnalisis.setImageBitmap(bitmap)

        if(idx==2 || idx==3){
            binding.tvHasilAnalisis.setBackgroundResource(R.drawable.rectangle_red)
            binding.tvHasilAnalisis.setTextColor(Color.RED)
            binding.tvHasilAnalisis.text = "UANG PALSU"
        }

        val detail = """<br>
            <b>Uang : </b> <span>${label.get(idx)}</span><br><br>
            <b>Probability : </b> <span>${String.format("%1.4f",prob*100)}%</span><br><br>
            <p>Hasil yang didapatkan dari aplikasi ini tidak sepenuhnya benar. 
            Anda dapat mengecek apakah uang ini palsu atau tidak secara manual. 
            Anda bisa melihat tips mengecek uang palsu atau tidak pada fitur aplikasi ini</p>
        """.trimIndent()

        binding.tvDetailAnalisis.setHtml(detail)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}