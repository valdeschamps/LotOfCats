package com.example.lotofcats.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lotofcats.model.Cat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val service: Webservice = retrofit.create(Webservice::class.java)
    var mutableLiveData: MutableLiveData<ArrayList<Cat>> = MutableLiveData()

    fun getData(catLimit: Int): LiveData<ArrayList<Cat>> {
        MutableLiveData<List<Cat>>()
        service.getData(limit = catLimit.toString())
            .enqueue(object : Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                    val result: ArrayList<Cat> = (mutableLiveData.value) ?: ArrayList()
                    response.body()?.forEach {
                        result.add(it)
                    }
                    mutableLiveData.value = result
                }

                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                    //todo
                }
            })
        return mutableLiveData
    }
}