package com.example.dibinisyr.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dibinisyr.adapter.SepetAdapter
import com.example.dibinisyr.entity.CRUDCevap
import com.example.dibinisyr.entity.Sepet
import com.example.dibinisyr.entity.SepetCevap
import com.example.dibinisyr.fragment.SepetFragment
import com.example.dibinisyr.retrofit.ApiUtils
import com.example.dibinisyr.retrofit.SepetDaoInterface
import com.example.dibinisyr.retrofit.YemekDaoInterface
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response


class SepetRepository {
    var sepetListesi: MutableLiveData<List<Sepet>>
    var sdao: SepetDaoInterface

    init {
        sdao = ApiUtils.getSepetDaoInterface()
        sepetListesi = MutableLiveData()

    }
    fun sepetGetir() : MutableLiveData<List<Sepet>> {
        return sepetListesi
    }

    fun sepetOnayla(kullanici_adi: String){
        sdao.sepetiGoster(kullanici_adi).enqueue(object : Callback<SepetCevap> {
            override fun onFailure(call: Call<SepetCevap>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<SepetCevap>?, response: Response<SepetCevap>) {
                Log.e("Sepet Onayla","Geldik")
                val liste = response.body().sepet_yemekler
                var listeyiSil =liste.toMutableList()
                listeyiSil.clear()
                sepetListesi.value=listeyiSil
            }

        })


    }

    fun sepetGoster(kullanici_adi:String){
        sdao.sepetiGoster(kullanici_adi).enqueue(object : Callback<SepetCevap>{
            override fun onFailure(call: Call<SepetCevap>?, t: Throwable?) {

            }
            override fun onResponse(call: Call<SepetCevap>?, response: Response<SepetCevap>) {
                val liste = response.body().sepet_yemekler

                liste.groupBy { it.yemek_adi }
                var sonUrunSil = liste.toMutableList()
                sonUrunSil.removeAt(0)
                //Log.e("Sepetteki ürünler","$liste")
                sepetListesi.value=sonUrunSil

            }


        })
    }
    fun sepeteEkle(yemek_adi:String,yemek_resim_adi:String,yemek_fiyat:Int,yemek_siparis_adet:Int,kullanici_adi:String){

        sdao.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(object  : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message

                Log.e("Sepetteki ürünler","$basari - $mesaj")
            }

            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {
                Log.e("Hata aldik","Nerde yanlış yaptık?")
            }
        })

    }




    fun sepetSil(sepet_yemek_id:Int,kullanici_adi: String){
        sdao.sepettenUrunSil(sepet_yemek_id,kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onFailure(call: Call<CRUDCevap>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body().success
                val mesaj = response.body().message
                Log.e("Sepet sil","$basari - $mesaj")
                sepetGoster("bugra")
            }
        })
    }
}