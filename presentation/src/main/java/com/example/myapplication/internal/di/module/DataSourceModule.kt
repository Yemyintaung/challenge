package com.example.myapplication.internal.di.module

import com.example.data.repo.home.CurrencyDataSource
import com.example.data.repo.home.CurrencyDataSourceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Singleton
    @Provides
    fun provideCurrencyRemoteDataSource(retrofit: Retrofit): CurrencyDataSource {
        return CurrencyDataSourceImpl(retrofit)
    }
}