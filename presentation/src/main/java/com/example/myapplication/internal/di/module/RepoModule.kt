package com.example.myapplication.internal.di.module

import com.example.data.repo.home.CurrencyDataSource
import com.example.data.repo.home.CurrencyRepositoryImpl
import com.example.domain.repo.CurrencyRepository

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideCurrencyRepo(currencyDataSource: CurrencyDataSource,): CurrencyRepository {
        return CurrencyRepositoryImpl(currencyDataSource)
    }
}