package com.example.catimages.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catimages.models.PagedCat
import com.example.catimages.models.ResponseStatus
import com.example.catimages.network.CatApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImagesViewModel : ViewModel() {

    private val _status = MutableLiveData<ResponseStatus>()

    val status: LiveData<ResponseStatus>
        get() = _status

    private val _cat = MutableLiveData<List<PagedCat>>()
    val catList: LiveData<List<PagedCat>>
        get() = _cat

    init {
        getPagedCats(9,1)
    }

    private fun getPagedCats(limit: Int, page: Int) {
        viewModelScope.launch {
            _status.value = ResponseStatus.LOADING
            try {
                withContext(Dispatchers.Main){
                    _cat.value = CatApi.retrofitService.getPagedCats("asc",18,3)
                    _status.value = ResponseStatus.DONE
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){

                    Log.d("ALLLL", "${e.message} ${e.localizedMessage}")
                    _status.value = ResponseStatus.ERROR
                    _cat.value = ArrayList()
                }
            }
        }
    }

}