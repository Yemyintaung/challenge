package com.example.data.repo.home

import com.example.domain.models.CurrencyExchange

interface CurrencyDataSource {
    suspend fun getCurrencyExchange(): CurrencyExchange
}