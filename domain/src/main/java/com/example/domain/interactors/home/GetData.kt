package com.example.domain.interactors.home

import com.example.domain.models.CurrencyExchange
import com.example.domain.repo.CurrencyRepository
import javax.inject.Inject

class GetData @Inject constructor(private val repository: CurrencyRepository) {
    suspend fun getCurrencyExchange(): CurrencyExchange {
        return repository.getCurrencyExchange()
    }
}