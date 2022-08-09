package com.example.itunesdemoactivity.data.api

import com.example.itunesdemoactivity.data.ItunesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search?")
    suspend fun getItunesInfo(@Query("term")searchingText:String): Response<ItunesResponse>
}