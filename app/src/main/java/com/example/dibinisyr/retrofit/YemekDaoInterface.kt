package com.example.dibinisyr.retrofit

import com.example.dibinisyr.entity.CRUDCevap
import com.example.dibinisyr.entity.SepetCevap
import com.example.dibinisyr.entity.YemekCevap
import retrofit2.Call
import retrofit2.http.*

interface YemekDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun tumYemekler() : Call<YemekCevap>


}