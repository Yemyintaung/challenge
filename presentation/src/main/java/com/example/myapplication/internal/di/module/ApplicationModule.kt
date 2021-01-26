package com.example.myapplication.internal.di.module

import android.content.Context
import com.example.myapplication.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideContext(app: MyApplication): Context {
        return app.applicationContext
    }
}