package com.example.myapplication.features.home.list

data class HomeResult (
    val loading: Boolean = false,
    val success: HomeUiModel? = null,
    val error: String? = null
)