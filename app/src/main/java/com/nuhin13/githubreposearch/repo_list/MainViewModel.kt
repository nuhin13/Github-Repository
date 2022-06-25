package com.nuhin13.githubreposearch.repo_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nuhin13.githubreposearch.api.MainRepository
import com.nuhin13.githubreposearch.data.RepositoryList
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val repoList = MutableLiveData<RepositoryList>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

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
}