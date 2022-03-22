package com.example.dibinisyr.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dibinisyr.entity.CRUDCevap
import com.example.dibinisyr.entity.Yemek
import com.example.dibinisyr.entity.YemekCevap
import com.example.dibinisyr.retrofit.ApiUtils
import com.example.dibinisyr.retrofit.SepetDaoInterface
import com.example.dibinisyr.retrofit.YemekDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemekRepository {
    var yemekListesi:MutableLiveData<List<Yemek>>
    var ydao:YemekDaoInterface
    var sdao:SepetDaoInterface

    init {
        ydao = ApiUtils.getYemeklerDaoInterface()
        sdao = ApiUtils.getSepetDaoInterface()
        yemekListesi = MutableLiveData()
    }

    fun yemekleriGetir() : MutableLiveData<List<Yemek>> {
        return yemekListesi
    }

    fun tumYemekleriAl(){
        Log.e("Yemek","Tüm Yemekleri al çalıştı")
        ydao.tumYemekler().enqueue(object : Callback<YemekCevap> {

            override fun onResponse(call: Call<YemekCevap>?, response: Response<YemekCevap>) {
                val liste = response.body().yemekler

                yemekListesi.value = liste.sortedByDescending{ it.yemek_fiyat.toInt() }.reversed()
               // Log.e("Yemek","${liste}")
            }

            override fun onFailure(call: Call<YemekCevap>?, t: Throwable?) {}
        })
    }



}