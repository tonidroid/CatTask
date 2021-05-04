package com.example.catimages.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.catimages.db.CatDao
import com.example.catimages.models.Cat
import com.example.catimages.repositories.CatNetworkRepositoryImpl
import kotlinx.coroutines.CoroutineScope

class CatDataSourceFactory(private val repository: CatNetworkRepositoryImpl,
                           private val catDao: CatDao,
                           private val scope: CoroutineScope
): DataSource.Factory<Int, Cat>() {

    val source = MutableLiveData<CatDataSource>()

    override fun create(): DataSource<Int, Cat> {
        val source = CatDataSource(repository, catDao, scope)
        this.source.postValue(source)
        return source
    }

}