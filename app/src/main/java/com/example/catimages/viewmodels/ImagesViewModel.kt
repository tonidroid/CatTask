package com.example.catimages.viewmodels


import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.catimages.datasource.CatDataSourceFactory
import com.example.catimages.models.Cat
import com.example.catimages.models.ResponseStatus
import com.example.catimages.repositories.CatRepositoryImpl
import com.example.catimages.ui.BaseViewModel

class ImagesViewModel : BaseViewModel() {

    private val catRepo: CatRepositoryImpl = CatRepositoryImpl()

    private val catDataSource = CatDataSourceFactory(catRepo, ioScope)

    val cats = LivePagedListBuilder(catDataSource, pagedListConfig()).build()
    val responseState: LiveData<ResponseStatus> =
        Transformations.switchMap(catDataSource.source) {
            Log.d("RESPONSE STATE", "${it.getResponseState().value}")
            it.getResponseState() }

//    private val _status = MutableLiveData<ResponseStatus>()
//    val status: LiveData<ResponseStatus>
//        get() = _status

    private val _cat = MutableLiveData<List<Cat>>()
    val catList: LiveData<List<Cat>>
        get() = _cat


//    init {
//        getPagedCats(3,0)
//    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(9)
        .setEnablePlaceholders(true)
        .setPageSize(9 * 2)
        .build()

//    fun getPagedCats(limit: Int, page: Int) {
//        viewModelScope.launch {
//            _status.value = ResponseStatus.LOADING
//            try {
//                    _cat.value = catRepo.getNetworkCats("asc",limit, page)
//                    _status.value = ResponseStatus.DONE
//            } catch (e: Exception) {
//                    Log.d("ALLLL", "${e.message} ${e.localizedMessage}")
//                    _status.value = ResponseStatus.ERROR
//                    _cat.value = ArrayList()
//            }
//        }
//    }

}