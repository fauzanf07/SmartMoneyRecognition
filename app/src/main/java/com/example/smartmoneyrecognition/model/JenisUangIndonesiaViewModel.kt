package com.example.smartmoneyrecognition.model

import androidx.lifecycle.ViewModel
import com.example.smartmoneyrecognition.R
import com.example.smartmoneyrecognition.data_class.rvJenisUang

class JenisUangIndonesiaViewModel: ViewModel() {

    fun getRvTitles() : ArrayList<String>{
        val rvTitleLists = ArrayList<String>()
        rvTitleLists.add("Uang Rp 100.000")
        rvTitleLists.add("Uang Rp 50.000")
        rvTitleLists.add("Uang Rp 20.000")
        rvTitleLists.add("Uang Rp 10.000")
        rvTitleLists.add("Uang Rp 5.000")
        rvTitleLists.add("Uang Rp 2.000")
        rvTitleLists.add("Uang Rp 1.000")
        return rvTitleLists
    }

    fun getRvImagesFront() : ArrayList<Int>{
        val rvImageFrontLists = ArrayList<Int>()
        rvImageFrontLists.add(R.drawable.front_seratus)
        rvImageFrontLists.add(R.drawable.front_limapuluh)
        rvImageFrontLists.add(R.drawable.front_duapuluh)
        rvImageFrontLists.add(R.drawable.front_sepuluh)
        rvImageFrontLists.add(R.drawable.front_limaribu)
        rvImageFrontLists.add(R.drawable.front_duaribu)
        rvImageFrontLists.add(R.drawable.front_seribu)
        return rvImageFrontLists
    }

    fun getRvImagesBack() : ArrayList<Int>{
        val rvImageBackLists = ArrayList<Int>()
        rvImageBackLists.add(R.drawable.back_seratus)
        rvImageBackLists.add(R.drawable.back_limapuluh)
        rvImageBackLists.add(R.drawable.back_duapuluh)
        rvImageBackLists.add(R.drawable.back_sepuluh)
        rvImageBackLists.add(R.drawable.back_limaribu)
        rvImageBackLists.add(R.drawable.back_duaribu)
        rvImageBackLists.add(R.drawable.back_seribu)
        return rvImageBackLists
    }


    fun getRvLists(): ArrayList<rvJenisUang>{
        val rvImageFront = getRvImagesFront()
        val rvImageBack = getRvImagesBack()
        val rvTitles = getRvTitles()
        val rvLists = ArrayList<rvJenisUang>()
        for(position in 0..rvTitles.size-1){
            val rvJenisUang = rvJenisUang(rvTitles.get(position),rvImageFront.get(position),rvImageBack.get(position))
            rvLists.add(rvJenisUang)
        }
        return rvLists
    }
}