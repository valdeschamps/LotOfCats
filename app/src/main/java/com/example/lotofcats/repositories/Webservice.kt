package com.example.lotofcats.repositories

import com.example.lotofcats.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface Webservice {
    @GET("/images/search?limit=10&page={page}")
    fun getData(@Path("page") page: Int, @Header("api") apikey: String = BuildConfig.APIKEY)
}