package com.mitroshin.trex

import android.app.Activity
import android.app.Application
import com.mitroshin.trex.di.ApplicationComponent
import com.mitroshin.trex.di.ApplicationInjector
import com.mitroshin.trex.di.DaggerApplicationComponent
import com.mitroshin.trex.di.modules.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = createComponent()
        ApplicationInjector.init(this, component)
    }

    protected fun createComponent(): ApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(ApplicationModule(this))
        .build()

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}