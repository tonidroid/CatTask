package com.example.catimages.datasource


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.catimages.db.CatDao
import com.example.catimages.models.Cat
import com.example.catimages.models.ResponseStatus
import com.example.catimages.repositories.CatNetworkRepositoryImpl
import kotlinx.coroutines.*

class CatDataSource (repository: CatNetworkRepositoryImpl, private val catDao: CatDao, corScope: CoroutineScope)
    : PageKeyedDataSource<Int, Cat>() {

    private var supervisorJob = SupervisorJob()
    private val responseState = MutableLiveData<ResponseStatus>()

    private val scope = corScope
    private val repo = repository

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Cat>) { }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Cat>) {
        executeQuery(1, params.requestedLoadSize) {

            callback.onResult(it, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Cat>) {
        val page = params.key
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page + 1)
        }
    }


    fun getResponseState(): LiveData<ResponseStatus> =
        responseState

    override fun invalidate() {
        super.invalidate()
        supervisorJob.cancelChildren()
    }


    private fun executeQuery(page: Int, perPage: Int, callback:(List<Cat>) -> Unit) {
        responseState.postValue(ResponseStatus.LOADING)
        scope.launch(getJobErrorHandler() + supervisorJob) {
            val cats = repo.getNetworkCats("asc", perPage, page)
            catDao.insert(cats)
            responseState.postValue(ResponseStatus.DONE)
            callback(cats)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.d("getJobErrorHandler", "Error: $e")
        responseState.postValue(ResponseStatus.ERROR)
    }

}