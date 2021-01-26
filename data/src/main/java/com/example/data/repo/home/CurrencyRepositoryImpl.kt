package com.example.data.repo.home

import com.example.domain.models.CurrencyExchange
import com.example.domain.repo.CurrencyRepository

class CurrencyRepositoryImpl(
    val source: CurrencyDataSource
) : CurrencyRepository {
    override suspend fun getCurrencyExchange(): CurrencyExchange {
        return source.getCurrencyExchange()
    }
}