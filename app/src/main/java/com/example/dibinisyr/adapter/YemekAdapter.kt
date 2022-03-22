package com.example.dibinisyr.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dibinisyr.databinding.CardTasarimBinding
import com.example.dibinisyr.entity.Yemek
import com.example.dibinisyr.fragment.AnasayfaFragmentDirections
import com.example.dibinisyr.retrofit.ApiUtils
import com.example.dibinisyr.viewmodel.AnasayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class YemekAdapter (var mContext: Context,var yemekListesi:List<Yemek>, var viewModel:AnasayfaFragmentViewModel) :
RecyclerView.Adapter<YemekAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim: CardTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: CardTasarimBinding

        init {
            this.tasarim = tasarim
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater =  LayoutInflater.from(mContext)
        val tasarim = CardTasarimBinding.inflate(layoutInflater,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val yemek = yemekListesi.get(position)
        val t = holder.tasarim
        val url = "${ApiUtils.BASE_URL}yemekler/resimler/${yemek.yemek_resim_adi}"
        Picasso.get().load(url).into(t.ivMSResim)

        t.yemekNesnesi = yemek
        t.cardViewYemek.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.yemekDetayGecis(yemek = yemek)  //yemekdetaygecişe yemek nesnesi ekle
            Navigation.findNavController(it).navigate(gecis)
        }
    }
    override fun getItemCount(): Int {
        return yemekListesi.size
    }

}