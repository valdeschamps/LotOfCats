package com.example.lotofcats.repositories

import com.example.lotofcats.BuildConfig
import com.example.lotofcats.model.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Webservice {
    //get a number of pictures equal to limit
    @GET("v1/images/search")
    suspend fun getData(
        @Query("limit") limit: String,
        @Header("x-api-key") apikey: String = BuildConfig.APIKEY
    ): Response<List<Cat>>
}