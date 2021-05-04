package com.example.catimages.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.catimages.db.CatDao

class ImagesViewModelFactory(
    private val catDao: CatDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImagesViewModel::class.java)) {
            return ImagesViewModel(catDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}