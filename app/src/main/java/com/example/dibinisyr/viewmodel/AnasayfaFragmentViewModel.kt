package com.example.dibinisyr.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dibinisyr.entity.Yemek
import com.example.dibinisyr.repo.SepetRepository
import com.example.dibinisyr.repo.YemekRepository

class AnasayfaFragmentViewModel : ViewModel(){
    var yemekListesi = MutableLiveData<List<Yemek>>()
    val yrepo = YemekRepository()


    init {
        yemekleriYukle()
        yemekListesi = yrepo.yemekleriGetir()
        Log.e("Yemekleri getir","$yemekListesi")
    }

    fun yemekleriYukle(){
        yrepo.tumYemekleriAl()
        Log.e("Yemekleri Yukle","Çalıştı")
    }


}