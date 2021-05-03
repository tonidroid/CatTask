package com.example.catimages.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catimages.models.Cat


class DetailViewModelFactory(
    private val cat: Cat
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(cat) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}