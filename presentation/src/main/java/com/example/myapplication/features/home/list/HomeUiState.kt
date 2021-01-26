package com.example.myapplication.features.home.list

data class HomeUiState (
    val amountError: Int? = null,
    val exchangeRateError: Int?= null,
    val result: Double?= null,
    val isDataValid: Boolean = false
)