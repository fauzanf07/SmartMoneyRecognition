package com.example.smartmoneyrecognition.model

import androidx.lifecycle.ViewModel
import com.example.smartmoneyrecognition.data_class.rvMataUang

class MataUangDuniaViewModel: ViewModel() {
    fun getRvCity() : ArrayList<String>{
        val rvCityLists = ArrayList<String>()
        rvCityLists.add("Arab Saudi")
        rvCityLists.add("Belanda")
        rvCityLists.add("Filipina")
        rvCityLists.add("Guam")
        rvCityLists.add("India")
        rvCityLists.add("Indonesia")
        rvCityLists.add("Jepang")
        rvCityLists.add("Jerman")
        rvCityLists.add("Korea Selatan")
        return rvCityLists
    }

    fun getRvCurrency() : ArrayList<String>{
        val rvCurrencyLists = ArrayList<String>()
        rvCurrencyLists.add("Real")
        rvCurrencyLists.add("Euro")
        rvCurrencyLists.add("Peso")
        rvCurrencyLists.add("Dollar AS")
        rvCurrencyLists.add("Rupee")
        rvCurrencyLists.add("Rupiah")
        rvCurrencyLists.add("Yen")
        rvCurrencyLists.add("Euro")
        rvCurrencyLists.add("Won")
        return rvCurrencyLists
    }

    fun getRvShortCurrency() : ArrayList<String>{
        val rvShortCurrencyLists = ArrayList<String>()
        rvShortCurrencyLists.add("(SAR)")
        rvShortCurrencyLists.add("(EUR)")
        rvShortCurrencyLists.add("(PHP)")
        rvShortCurrencyLists.add("(USD)")
        rvShortCurrencyLists.add("(INR)")
        rvShortCurrencyLists.add("(IDR)")
        rvShortCurrencyLists.add("(JPY)")
        rvShortCurrencyLists.add("(EUR)")
        rvShortCurrencyLists.add("(KRW)")
        return rvShortCurrencyLists
    }

    fun getRvMataUangLists(): ArrayList<rvMataUang>{
        val rvCity = getRvCity()
        val rvCurrency = getRvCurrency()
        val rvShortCurrency = getRvShortCurrency()
        val rvMataUangLists = ArrayList<rvMataUang>()
        for(position in 0..rvCity.size-1){
            val rvMataUang = rvMataUang(rvCity.get(position),rvCurrency.get(position),rvShortCurrency.get(position))
            rvMataUangLists.add(rvMataUang)
        }
        return rvMataUangLists
    }
}