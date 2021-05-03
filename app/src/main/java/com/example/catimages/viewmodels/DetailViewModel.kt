package com.example.catimages.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catimages.models.Cat

class DetailViewModel (cat: Cat) : ViewModel() {

    private val _selectedCat = MutableLiveData<Cat>()
    val selectedCat: LiveData<Cat>
        get() = _selectedCat

    init {
        _selectedCat.value = cat
    }
}