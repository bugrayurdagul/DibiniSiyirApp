package com.example.dibinisyr.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.dibinisyr.R
import com.example.dibinisyr.adapter.YemekAdapter
import com.example.dibinisyr.databinding.CardTasarimBinding
import com.example.dibinisyr.databinding.FragmentAnasayfaBinding
import com.example.dibinisyr.viewmodel.AnasayfaFragmentViewModel
import com.squareup.picasso.Picasso

class AnasayfaFragment : Fragment() {
    private lateinit var tasarim:FragmentAnasayfaBinding
    private lateinit var viewModel:AnasayfaFragmentViewModel
    private var bs = YemekDetayFragment()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false)

        tasarim.anasayfaFragment = this
        tasarim.anasayfaToolbarBaslik = ""
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnasayfa)
        tasarim.toolbarTitle.text = "Dibini Sıyır"

        viewModel.yemekListesi.observe(viewLifecycleOwner,{
            val adapter = YemekAdapter(requireContext(),it,viewModel)
            tasarim.yemekAdapter = adapter
        })


        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel


    }



}