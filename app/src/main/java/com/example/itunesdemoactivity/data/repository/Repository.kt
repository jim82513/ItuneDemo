package com.example.itunesdemoactivity.data.repository

import com.example.itunesdemoactivity.data.api.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    suspend fun getEmployee(searchingText:String) = apiService.getItunesInfo(searchingText)
}