package com.mitroshin.trex.di

import com.mitroshin.trex.MyApplication
import com.mitroshin.trex.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,

    ActivityBuildersModule::class,
    ApplicationModule::class,
    NetworkModule::class
])
interface ApplicationComponent {

    fun inject(application: MyApplication)
}