package com.example.dibinisyr.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dibinisyr.entity.Sepet
import com.example.dibinisyr.repo.SepetRepository

class SepetFragmentViewModel : ViewModel(){
    var sepetListesi = MutableLiveData<List<Sepet>>()
    val srepo = SepetRepository()


    init {
        sepetiYukle()
        sepetListesi = srepo.sepetGetir()
    }

    fun sepetiYukle(){
        srepo.sepetGoster("bugra")
    }

    fun sepetTamam(){
        srepo.sepetOnayla("bugra")
    }

    fun sepettenUrunSil(sepet_yemek_id:Int){
        srepo.sepetSil(sepet_yemek_id,"bugra")
        sepetiYukle()
    }

    fun yemekGuncelle (yemek_adi : String,
    yemek_resim_adi : String,
    yemek_fiyat : Int,
    yemek_siparis_adet : Int,
    kullanici_adi : String){
        srepo.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)}

}