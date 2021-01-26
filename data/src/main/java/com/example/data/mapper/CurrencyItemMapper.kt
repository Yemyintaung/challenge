package com.example.data.mapper

import com.example.data.model.currency.CurrencyApiResponse
import com.example.domain.models.CurrencyExchange
import com.google.gson.Gson

class CurrencyItemMapper {
    fun map(data: CurrencyApiResponse): CurrencyExchange {
        val map = Gson().fromJson(data.quotes, Map::class.java) as Map<String, Double>
        return CurrencyExchange(map)
    }
}