package com.example.lotofcats.repositories

import androidx.lifecycle.MutableLiveData
import com.example.lotofcats.model.Cat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: Webservice = retrofit.create(Webservice::class.java)
    var mutableLiveData: MutableLiveData<ArrayList<Cat>> = MutableLiveData()
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main

    suspend fun getData(catLimit: Int){
        val response = service.getData(limit = catLimit.toString())
        withContext(mainDispatcher){
            if(response.isSuccessful){
                val result: ArrayList<Cat> = (mutableLiveData.value) ?: ArrayList()
                response.body()?.forEach {
                    result.add(it)
                }
                mutableLiveData.value = result
            }else{
                //todo notify viewmodel with response.message()
            }
        }
    }
}