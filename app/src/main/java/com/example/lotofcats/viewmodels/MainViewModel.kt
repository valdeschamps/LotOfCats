package com.example.lotofcats.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lotofcats.model.Cat
import com.example.lotofcats.repositories.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {
    companion object {
        val SORT_ORDER_DESC = "DESC"
    }

    private val repo: Repository by inject()
    var catLimit = 10
    private var pageNumber = 0
    var catList: MutableLiveData<ArrayList<Cat>> = repo.mutableLiveData

    fun fetchData() {
        pageNumber += 1
        repo.getData(catLimit, pageNumber, SORT_ORDER_DESC)
    }
}
