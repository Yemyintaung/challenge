package com.example.myapplication.features.home.list

import androidx.lifecycle.*
import com.example.domain.interactors.home.GetData
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.myapplication.R

class HomeViewModel @Inject constructor(
    private val getData: GetData
) : ViewModel() {

    // REPOSITORY SECTION
    fun getDataFromRepository() = liveData {
        emit(HomeResult(loading = true))
        val result = getData.getCurrencyExchange()
        val currencies = getCurrencies(result.quotes.keys.toMutableList())
        val exchangeRates = result.quotes.values.toMutableList()

        try {
            emit(HomeResult(loading = false, success = HomeUiModel(currencies, exchangeRates)))
        } catch (e: Exception) {
            emit(HomeResult(loading = false, error = e.localizedMessage.toString()))
        }
    }

    private fun getCurrencies(list: List<String>): ArrayList<String> {
        val currencyNameList = ArrayList<String>()
        for ((index, value) in list.withIndex()) {
            currencyNameList.add(value.removePrefix("USD"))
        }

        return currencyNameList
    }

    // UI SECTION
    fun toUSD(amount: String, rate: Double) =
        liveData<HomeUiState> {
            val usdAmount = amount.toDoubleOrNull()

            when {
                usdAmount == null -> {
                    emit(
                        HomeUiState(
                            amountError = R.string.error_invalid_amount,
                            exchangeRateError = null,
                            isDataValid = false
                        )
                    )
                }
                validateExchangeRate(rate) -> {
                    emit(
                        HomeUiState(
                            exchangeRateError = R.string.error_exchange_rate,
                            isDataValid = false
                        )
                    )
                }
                else -> {
                    emit(
                        HomeUiState(
                            exchangeRateError = null,
                            result = calculateUsdRate(usdAmount, rate),
                            isDataValid = true
                        )
                    )
                }
            }

        }

    fun fromUSD(amount: String, rate: Double) =
        liveData<HomeUiState> {
            val otherCurrencyAmount = amount.toDoubleOrNull()

            when {
                otherCurrencyAmount == null -> {
                    emit(
                        HomeUiState(
                            amountError = R.string.error_invalid_amount,
                            exchangeRateError = null,
                            isDataValid = false
                        )
                    )
                }
                validateExchangeRate(rate) -> {
                    emit(
                        HomeUiState(
                            exchangeRateError = R.string.error_exchange_rate,
                            isDataValid = false
                        )
                    )
                }
                else -> {
                    emit(
                        HomeUiState(
                            exchangeRateError = null,
                            result = calculateOtherRate(otherCurrencyAmount, rate),
                            isDataValid = true
                        )
                    )
                }
            }

        }

    private fun validateExchangeRate(rate: Double): Boolean {
        return rate.isNaN()
    }

    private fun calculateUsdRate(amount: Double, rate: Double): Double {
        return amount / rate
    }

    private fun calculateOtherRate(
        amount: Double,
        rate: Double
    ): Double {
        return amount * rate
    }

    override fun onCleared() {
        super.onCleared()
    }
}