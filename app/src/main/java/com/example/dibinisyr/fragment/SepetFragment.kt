package com.example.dibinisyr.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.dibinisyr.R
import com.example.dibinisyr.adapter.SepetAdapter
import com.example.dibinisyr.databinding.FragmentSepetBinding
import com.example.dibinisyr.entity.Sepet
import com.example.dibinisyr.repo.SepetRepository
import com.example.dibinisyr.viewmodel.SepetFragmentViewModel
import com.google.android.material.snackbar.Snackbar


class SepetFragment : Fragment() {
    private lateinit var tasarim : FragmentSepetBinding
    private lateinit var viewModel:SepetFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_sepet,container,false)
        tasarim.sepetFragment = this

        tasarim.sepetToolbarBaslik = ""
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarSepet)
        tasarim.toolbarTitle.text = "Sepetinizdeki Ürünler"
        viewModel.sepetListesi.observe(viewLifecycleOwner,{

            val adapter = SepetAdapter(requireContext(),it,viewModel)
            tasarim.sepetAdapter = adapter

        })
        tasarim.btnSepetiOnayla.setOnClickListener {
            Snackbar.make(it,"Sepet Onaylansın mı?",Snackbar.LENGTH_LONG).setAction("Evet"){
                sepetiOnayla()
            }.show()
        }
        return tasarim.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel

    }

    fun sepetiOnayla(){
        viewModel.sepetTamam()
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle()
    }

}