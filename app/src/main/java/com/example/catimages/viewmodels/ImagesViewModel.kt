package com.example.catimages.viewmodels


import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.catimages.datasource.CatDataSourceFactory
import com.example.catimages.db.CatDao
import com.example.catimages.models.Cat
import com.example.catimages.models.ResponseStatus
import com.example.catimages.repositories.CatLocalRepositoryImpl
import com.example.catimages.repositories.CatNetworkRepositoryImpl
import com.example.catimages.ui.BaseViewModel
import kotlinx.coroutines.launch

class ImagesViewModel (catDao: CatDao) : BaseViewModel() {

    private val catNetworkRepo: CatNetworkRepositoryImpl = CatNetworkRepositoryImpl()
    private val catLocalRepo: CatLocalRepositoryImpl = CatLocalRepositoryImpl(catDao)

    private val catDataSource = CatDataSourceFactory(catNetworkRepo, catDao, ioScope)


    private val _localCats = MutableLiveData<List<Cat>>()
    val localCats: LiveData<List<Cat>>
        get() = _localCats

    val cats = LivePagedListBuilder(catDataSource, pagedListConfig()).build()
    val responseState: LiveData<ResponseStatus> =
        Transformations.switchMap(catDataSource.source) {
            Log.d("RESPONSE STATE", "${it.getResponseState().value}")
            it.getResponseState() }

    init {
        getCachedCats()
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(8)
        .setEnablePlaceholders(false)
        .setPageSize(4*2)
        .build()

    private fun getCachedCats() {
        viewModelScope.launch {
            try {
                _localCats.value = catLocalRepo.catDao.getAll()

                Log.d("LOCALCATS", "${localCats.value}")
            } catch (e: Exception) {
                _localCats.value = ArrayList()
            }
        }
    }


    fun deleteAllCachedCats() {
        viewModelScope.launch {
            catLocalRepo.catDao.deleteAll()
        }
    }

    fun deleteCachedCat( cat: Cat) = viewModelScope.launch {
        catLocalRepo.catDao.deleteById(cat.id)
    }

}