package com.example.myapplication.features.home.list

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.internal.base.BaseActivity
import com.example.myapplication.utils.afterTextChanged
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    private lateinit var binding: ActivityHomeBinding
    private lateinit var exchangeRate: List<Double>
    private var isAmountOneFocus: Boolean = false
    private var isAmountTwoFocus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeResultData()
    }

    private fun registerEvents() {
        binding.activityHomeEditAmount1.afterTextChanged {
            if (isAmountOneFocus) {
                usdExchangeObserver()
            }
        }

        binding.activityHomeEditAmount2.afterTextChanged {
            if (isAmountTwoFocus) {
                otherExchangeObserver()
            }
        }

        binding.activityHomeSpinnerCurrency1.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (isAmountOneFocus) {
                        usdExchangeObserver()
                    } else {
                        otherExchangeObserver()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
    }

    private fun observeResultData() {
        viewModel.getDataFromRepository().observe(this@HomeActivity, Observer {
            val homeResult = it ?: return@Observer

            if (homeResult.loading) {
                showLoadingDialog("Loading")
            } else {
                hideLoadingDialog()
            }

            if (homeResult.error != null) {

            }

            if (homeResult.success != null) {
                updateUiWithCurrency(homeResult.success)
            }
            setResult(Activity.RESULT_OK)
        })
    }

    private fun updateUiWithCurrency(homeUiModel: HomeUiModel) {
        setUpSpinner(homeUiModel.currencyList)
        exchangeRate = homeUiModel.exchangeRateList
        checkFocus()
        registerEvents()
    }

    private fun usdExchangeObserver() {
        val rate = exchangeRate[binding.activityHomeSpinnerCurrency1.selectedItemPosition]
        viewModel.toUSD(
            binding.activityHomeEditAmount1.text.toString(),
            rate
        ).observe(this@HomeActivity, Observer {
            val uiResult = it ?: return@Observer
            if (uiResult.amountError != null) {
                binding.activityHomeEditAmount1.error = getString(uiResult.amountError)
            }

            if (uiResult.exchangeRateError != null) {
                Toast.makeText(
                    this@HomeActivity,
                    getString(uiResult.exchangeRateError),
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (uiResult.isDataValid) {
                binding.activityHomeEditAmount2.error = null
                binding.activityHomeEditAmount2.setText("${uiResult.result}")
                binding.activityHomeTextExchangeRate?.text =
                    "1 USD = $rate ${binding.activityHomeSpinnerCurrency1.selectedItem}"
            }
        })
    }

    private fun otherExchangeObserver() {
        val rate = exchangeRate[binding.activityHomeSpinnerCurrency1.selectedItemPosition]
        viewModel.fromUSD(
            binding.activityHomeEditAmount2.text.toString(),
            rate
        ).observe(this@HomeActivity, Observer {
            val uiResult = it ?: return@Observer
            if (uiResult.amountError != null) {
                binding.activityHomeEditAmount2.error = getString(uiResult.amountError)
            }

            if (uiResult.exchangeRateError != null) {
                Toast.makeText(
                    this@HomeActivity,
                    getString(uiResult.exchangeRateError),
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (uiResult.isDataValid) {
                binding.activityHomeEditAmount1.error = null
                binding.activityHomeEditAmount1.setText("${uiResult.result}")
                binding.activityHomeTextExchangeRate?.text =
                    "1 USD = $rate ${binding.activityHomeSpinnerCurrency1.selectedItem}"
            }
        })
    }

    private fun checkFocus() {
        binding.activityHomeEditAmount1.onFocusChangeListener =
            View.OnFocusChangeListener { _, b -> isAmountOneFocus = b }

        binding.activityHomeEditAmount2.onFocusChangeListener =
            View.OnFocusChangeListener { _, b -> isAmountTwoFocus = b }
    }

    private fun setUpSpinner(list: ArrayList<String>) {
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, list
        )
        binding.activityHomeSpinnerCurrency1.adapter = adapter
    }


}