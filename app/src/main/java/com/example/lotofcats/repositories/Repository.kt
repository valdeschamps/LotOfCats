package com.example.lotofcats.repositories

import android.util.Log
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

    fun getData(catLimit: Int, pageNumber: Int, sortBy: String): LiveData<List<Cat>> {
        val data = MutableLiveData<List<Cat>>()
        service.getData(catLimit.toString(), pageNumber.toString(), sortBy)
            .enqueue(object : Callback<List<Cat>> {
                override fun onResponse(call: Call<List<Cat>>, response: Response<List<Cat>>) {
                    Log.d("test", response.toString())
                    data.value = response.body()
                }

                override fun onFailure(call: Call<List<Cat>>, t: Throwable) {
                    Log.d("test", "fail")
                    Log.d("test", "${t.message}")
                }
            })
        return data
    }
}