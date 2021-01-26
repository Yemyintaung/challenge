package com.example.myapplication.internal.di.component

import com.example.myapplication.MyApplication
import com.example.myapplication.internal.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class, AndroidInjectionModule::class, ApplicationModule::class,
            ActivityModule::class, FragmentModule::class, ViewModelModule::class, NetworkModule::class, RepoModule::class,
            DataSourceModule::class]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: MyApplication)
}