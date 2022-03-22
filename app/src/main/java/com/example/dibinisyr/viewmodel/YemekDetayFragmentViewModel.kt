package com.example.dibinisyr.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dibinisyr.repo.SepetRepository
import com.example.dibinisyr.repo.YemekRepository

class YemekDetayFragmentViewModel : ViewModel(){

    val srepo = SepetRepository()

    fun sepetEkle(yemek_adi : String,
                  yemek_resim_adi : String,
                  yemek_fiyat : Int,
                  yemek_siparis_adet : Int,
                  kullanici_adi : String){
        srepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }



}