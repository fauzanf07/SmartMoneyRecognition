package com.example.smartmoneyrecognition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.smartmoneyrecognition.databinding.ActivityMainBinding
import com.example.smartmoneyrecognition.fragments.CameraFragment
import com.example.smartmoneyrecognition.fragments.HeadlineFragment
import com.example.smartmoneyrecognition.fragments.HomeFragment
import com.example.smartmoneyrecognition.fragments.ProfilFragment
import com.example.smartmoneyrecognition.model.HomeViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val firstFragment=HomeFragment()
        val secondFragment=CameraFragment()
        val thirdFragment=HeadlineFragment()
        val fourthFragment = ProfilFragment()

        setCurrentFragment(firstFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->setCurrentFragment(firstFragment)
                R.id.camera->setCurrentFragment(secondFragment)
                R.id.headline->setCurrentFragment(thirdFragment)
                R.id.profil->setCurrentFragment(fourthFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}