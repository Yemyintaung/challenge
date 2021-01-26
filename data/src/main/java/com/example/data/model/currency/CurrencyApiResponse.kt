package com.example.data.model.currency

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CurrencyApiResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("terms")
    val terms: String,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("source")
    val source: String,
    @SerializedName("quotes")
    val quotes: JsonObject,
)


