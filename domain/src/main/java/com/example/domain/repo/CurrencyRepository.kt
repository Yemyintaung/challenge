package com.example.domain.repo

import com.example.domain.models.CurrencyExchange

interface CurrencyRepository {
    suspend fun getCurrencyExchange(): CurrencyExchange
}