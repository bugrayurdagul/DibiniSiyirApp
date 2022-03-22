package com.example.dibinisyr.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.dibinisyr.R
import com.example.dibinisyr.databinding.ActivityMainBinding
import com.example.dibinisyr.databinding.FragmentYemekDetayBinding
import com.example.dibinisyr.entity.Yemek
import com.example.dibinisyr.retrofit.ApiUtils
import com.example.dibinisyr.viewmodel.YemekDetayFragmentViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso


class YemekDetayFragment : BottomSheetDialogFragment(){
    private lateinit var tasarim:FragmentYemekDetayBinding
    private lateinit var viewModel:YemekDetayFragmentViewModel
    private lateinit var t:ActivityMainBinding
    companion object {
       private lateinit var gelenYemek:Yemek
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_yemek_detay,container,false)

        val bundle:YemekDetayFragmentArgs by navArgs()
        gelenYemek = bundle.yemek
        tasarim.yemekDetayFragment = this
        tasarim.yemekDetayToolbarBaslik = " "
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarYemekDetay).apply {
            tasarim.toolbarTitle.text = "Ürün Detayları"
        }
        val url = "${ApiUtils.BASE_URL}yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Picasso.get().load(url).into(tasarim.ivYD)
        if (::t.isInitialized){
            t.bottomNav.visibility = View.GONE
        }


        tasarim.yemekNesnesi = gelenYemek

        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemekDetayFragmentViewModel by viewModels()
        viewModel = tempViewModel


    }
    fun anaSayfaDon(v:View){
        Navigation.findNavController(v).navigate(R.id.yemekDetayAnasayfaGecis)
    }


    fun sepetEkleTikla(yemek_adi:String,
                       yemek_resim_adi: String,
                       yemek_fiyat:Int,
                       yemek_siparis_adet:Int,
                       kullanici_adi:String,
                       v:View){
            viewModel.sepetEkle(
                yemek_adi,
                yemek_resim_adi,
                yemek_fiyat,
                yemek_siparis_adet,
                kullanici_adi
            )
            Navigation.findNavController(v).navigate(R.id.yemekDetayAnasayfaGecis)

    }


}