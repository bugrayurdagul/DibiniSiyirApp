package com.example.dibinisyr.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dibinisyr.databinding.SepetTasarimBinding
import com.example.dibinisyr.entity.Sepet
import com.example.dibinisyr.retrofit.ApiUtils
import com.example.dibinisyr.viewmodel.SepetFragmentViewModel
import com.squareup.picasso.Picasso

class SepetAdapter (var mContext: Context, var sepetListesi:List<Sepet>, var viewModel: SepetFragmentViewModel) :
    RecyclerView.Adapter<SepetAdapter.SepetTasarimTutucu>() {

    inner class SepetTasarimTutucu(tasarim: SepetTasarimBinding) :
        RecyclerView.ViewHolder(tasarim.root) {
        var tasarim: SepetTasarimBinding

        init {
            this.tasarim = tasarim

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetTasarimTutucu {
        val layoutInflater =  LayoutInflater.from(mContext)
        val tasarim = SepetTasarimBinding.inflate(layoutInflater,parent,false)
        return SepetTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: SepetTasarimTutucu, position: Int) {
        val sepet = sepetListesi.sortedBy{it.yemek_fiyat}.get(position)
        val t = holder.tasarim

        Log.e("Sepet isempty","${sepetListesi.isEmpty()}")
        val url = "${ApiUtils.BASE_URL}yemekler/resimler/${sepet.yemek_resim_adi}"
        Picasso.get().load(url).into(t.ivYemekResimSepet)
        t.sepetNesnesi = sepet

        Log.e("Sepet Listesi değişkeni","$sepetListesi")

        t.ivUrunAdetAzalt.setOnClickListener {
            if (t.etSepetAdet.text.toString() != "1") {
                viewModel.sepettenUrunSil(sepet.sepet_yemek_id)
                viewModel.yemekGuncelle(
                    sepet.yemek_adi,
                    sepet.yemek_resim_adi,
                    sepet.yemek_fiyat,
                    t.etSepetAdet.text.toString().toInt() - 1,
                        "bugra"
                    )
                notifyItemRemoved(position)
                    //viewModel.sepetiYukle()
                    Log.e("İf", "içinde")
                } else {
                    viewModel.sepettenUrunSil(sepet.sepet_yemek_id)
                    sepetListesi.get(0)
                    Log.e("Else", "içinde")
                }
            }

        t.ivUrunAdetEkle.setOnClickListener {

            viewModel.sepettenUrunSil(sepet.sepet_yemek_id)
            viewModel.yemekGuncelle(
                sepet.yemek_adi,
                sepet.yemek_resim_adi,
                sepet.yemek_fiyat,
                t.etSepetAdet.text.toString().toInt() + 1,
                "bugra"
                )

            }

    }

    override fun getItemCount(): Int {
        return sepetListesi.size
    }

}