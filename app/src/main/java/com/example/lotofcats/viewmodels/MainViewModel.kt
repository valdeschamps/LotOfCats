package com.example.lotofcats.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lotofcats.model.Cat
import com.example.lotofcats.repositories.Repository

class MainViewModel : ViewModel() {
    private val repo = Repository()
    val string: LiveData<List<Cat>> = repo.getData()
}
