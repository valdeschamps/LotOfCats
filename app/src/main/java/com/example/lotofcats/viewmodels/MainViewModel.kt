package com.example.lotofcats.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lotofcats.model.Cat
import com.example.lotofcats.repositories.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    private val repo: Repository by inject()
    var catLimit = 10
    var catList: MutableLiveData<ArrayList<Cat>> = repo.mutableLiveData
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    var errorMessage: MutableLiveData<String> = repo.errorMessage

    fun fetchData() {
        viewModelScope.launch(ioDispatcher){
            repo.getData(catLimit)
        }
    }
}
