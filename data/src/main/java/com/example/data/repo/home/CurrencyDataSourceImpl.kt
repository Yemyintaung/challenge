package com.example.data.repo.home

import com.example.data.BuildConfig
import com.example.data.mapper.CurrencyItemMapper
import com.example.data.model.currency.CurrencyApiResponse
import com.example.data.service.CurrencyApiService
import com.example.domain.models.CurrencyExchange
import retrofit2.Retrofit

class CurrencyDataSourceImpl(private val retrofit: Retrofit): CurrencyDataSource {
    private val apiService by lazy { retrofit.create(CurrencyApiService::class.java) }
    private val mapper by lazy { CurrencyItemMapper() }
    override suspend fun getCurrencyExchange(): CurrencyExchange {
        return mapper.map(apiService.getCurrencyExchange(BuildConfig.ACCESS_TOKEN))
    }
}