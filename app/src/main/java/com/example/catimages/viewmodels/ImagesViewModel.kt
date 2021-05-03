package com.example.catimages.viewmodels


import android.util.Log
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.catimages.datasource.CatDataSourceFactory
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

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(8)
        .setEnablePlaceholders(false)
        .setPageSize(4*2)
        .build()

}