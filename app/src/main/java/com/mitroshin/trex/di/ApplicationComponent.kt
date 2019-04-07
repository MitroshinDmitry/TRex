package com.mitroshin.trex.di

import com.mitroshin.trex.MyApplication
import com.mitroshin.trex.di.modules.ActivityBuildersModule
import com.mitroshin.trex.di.modules.ApplicationModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidInjectionModule::class,

    ActivityBuildersModule::class,
    ApplicationModule::class
])
interface ApplicationComponent {

    fun inject(application: MyApplication)
}