package com.example.lotofcats.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lotofcats.model.Cat
import com.example.lotofcats.repositories.Repository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {
    companion object {
        val SORT_ORDER_DESC = "DESC"
    }

    private val repo : Repository by inject()
    private var catLimit = 5
    private var pageNumber = 0

    val string: LiveData<List<Cat>> = repo.getData(catLimit, pageNumber, SORT_ORDER_DESC)
}
