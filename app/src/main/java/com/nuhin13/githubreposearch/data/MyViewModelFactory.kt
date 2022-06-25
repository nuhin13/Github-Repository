package com.nuhin13.githubreposearch.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nuhin13.githubreposearch.repo_list.MainViewModel
import com.nuhin13.githubreposearch.api.MainRepository

class MyViewModelFactory constructor(private val repository: MainRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}