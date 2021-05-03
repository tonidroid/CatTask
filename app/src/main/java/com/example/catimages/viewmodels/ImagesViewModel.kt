package com.example.catimages.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catimages.models.Cat
import com.example.catimages.models.ResponseStatus
import com.example.catimages.repositories.CatRepositoryImpl
import kotlinx.coroutines.launch

class ImagesViewModel : ViewModel() {

    private val catRepo: CatRepositoryImpl = CatRepositoryImpl()

    private val _status = MutableLiveData<ResponseStatus>()
    val status: LiveData<ResponseStatus>
        get() = _status

    private val _cat = MutableLiveData<List<Cat>>()
    val catList: LiveData<List<Cat>>
        get() = _cat


    init {
        getPagedCats(3,0)
    }

    fun getPagedCats(limit: Int, page: Int) {
        viewModelScope.launch {
            _status.value = ResponseStatus.LOADING
            try {
                    _cat.value = catRepo.getNetworkCats("asc",limit, page)
                    _status.value = ResponseStatus.DONE
            } catch (e: Exception) {
                    Log.d("ALLLL", "${e.message} ${e.localizedMessage}")
                    _status.value = ResponseStatus.ERROR
                    _cat.value = ArrayList()
            }
        }
    }

}