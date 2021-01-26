package com.example.data.service

import com.example.data.model.currency.CurrencyApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("/live")
    suspend fun getCurrencyExchange(@Query("access_key") accessKey: String): CurrencyApiResponse
}