package com.example.catimages.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.catimages.models.Cat
import com.example.catimages.repositories.CatRepositoryImpl
import kotlinx.coroutines.CoroutineScope

class CatDataSourceFactory(private val repository: CatRepositoryImpl,
                           private val scope: CoroutineScope
): DataSource.Factory<Int, Cat>() {

    val source = MutableLiveData<CatDataSource>()

    override fun create(): DataSource<Int, Cat> {
        val source = CatDataSource(repository, scope)
        this.source.postValue(source)
        return source
    }

}