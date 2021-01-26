package com.example.myapplication.internal.di.module

import android.content.Context
import com.example.data.network.RestAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return RestAdapter.create()
    }
}