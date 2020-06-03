package com.example.lotofcats.repositories

import com.example.lotofcats.BuildConfig
import com.example.lotofcats.model.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface Webservice {
    @GET("v1/images/search?limit=5&page=0&order=Desc")
    fun getData(
        /*@Query("limit") limit: String = "1",
        @Query("page") page: String = "0",
        @Query("page") order: String = "DESC",*/
        @Header("x-api-key") apikey: String = BuildConfig.APIKEY
    ): Call<List<Cat>>
}