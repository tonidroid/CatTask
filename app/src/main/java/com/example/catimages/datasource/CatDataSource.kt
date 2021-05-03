package com.example.catimages.datasource


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.catimages.models.Cat
import com.example.catimages.models.ResponseStatus
import com.example.catimages.repositories.CatRepositoryImpl
import kotlinx.coroutines.*

class CatDataSource (repository: CatRepositoryImpl, corScope: CoroutineScope)
    : PageKeyedDataSource<Int, Cat>() {

    private var supervisorJob = SupervisorJob()
    private val responseState = MutableLiveData<ResponseStatus>()

    private val scope = corScope
    private val repo = repository

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Cat>) { }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Cat>) {
        Log.d("getJobErrorHandler", "load after")
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
            val users = repo.getNetworkCats("asc", perPage, page)
            responseState.postValue(ResponseStatus.DONE)
            callback(users)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.d("getJobErrorHandler", "Error: $e")
        responseState.postValue(ResponseStatus.ERROR)
    }

}