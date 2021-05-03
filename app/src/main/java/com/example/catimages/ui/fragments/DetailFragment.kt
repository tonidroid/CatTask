package com.example.catimages.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.catimages.R
import com.example.catimages.databinding.FragmentDetailBinding
import com.example.catimages.viewmodels.DetailViewModel
import com.example.catimages.viewmodels.DetailViewModelFactory


class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val selectedCat = DetailFragmentArgs.fromBundle(requireArguments()).selectedCat
        val viewModelFactory =  DetailViewModelFactory(selectedCat)
        binding.viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DetailViewModel::class.java)
        return binding.root
    }

}