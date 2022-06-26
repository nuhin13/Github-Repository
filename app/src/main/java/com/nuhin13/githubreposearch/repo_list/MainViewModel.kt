package com.nuhin13.githubreposearch.repo_list

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nuhin13.githubreposearch.api.MainRepository
import com.nuhin13.githubreposearch.cache.DataStoreImpl
import com.nuhin13.githubreposearch.data.RepositoryList
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val repoList = MutableLiveData<RepositoryList>()
    val loading = MutableLiveData<Boolean>()

    var position : MutableLiveData<Int> = MutableLiveData(0)

    var job: Job? = null
    private lateinit var storeRepository: DataStoreImpl

    fun storeInit(context: Context) {
        storeRepository = DataStoreImpl(context)
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getTopRepository(sort:String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = mainRepository.getAllRepository(sort.lowercase())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    repoList.postValue(response.body())

                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun saveData(position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            storeRepository.saveSortPosition(position)
        }
    }

    fun retrieveDate() {
        viewModelScope.launch(Dispatchers.IO) {
            storeRepository.getSortPosition().collect {
                position.postValue(it)
            }
        }
    }
}