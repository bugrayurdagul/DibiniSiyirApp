package com.example.dibinisyr.retrofit

import com.example.dibinisyr.entity.CRUDCevap
import com.example.dibinisyr.entity.SepetCevap
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SepetDaoInterface {

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun sepeteEkle(@Field("yemek_adi") yemek_adi:String,
                   @Field("yemek_resim_adi") yemek_resim_adi:String,
                   @Field("yemek_fiyat") yemek_fiyat:Int,
                   @Field("yemek_siparis_adet")yemek_siparis_adet:Int,
                   @Field("kullanici_adi") kullanici_adi:String):Call<CRUDCevap>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun sepetiGoster(@Field("kullanici_adi")kullanici_adi: String) : Call<SepetCevap>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun sepettenUrunSil(@Field("sepet_yemek_id")sepet_yemek_id:Int,
                        @Field("kullanici_adi")kullanici_adi: String) : Call<CRUDCevap>

}