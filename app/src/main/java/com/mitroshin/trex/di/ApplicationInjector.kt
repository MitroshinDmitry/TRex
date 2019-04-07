package com.mitroshin.trex.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.mitroshin.trex.MyApplication
import dagger.android.AndroidInjection
import dagger.android.support.HasSupportFragmentInjector

object ApplicationInjector {

    private val activityLifecycleCallback by lazy {
        object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {}

            override fun onActivityResumed(activity: Activity?) {}

            override fun onActivityStarted(activity: Activity?) {}

            override fun onActivityDestroyed(activity: Activity?) {}

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {}

            override fun onActivityStopped(activity: Activity?) {}

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                handleActivityCreated(activity!!)
            }
        }
    }

    fun init(application: MyApplication, component: ApplicationComponent) {

        component.inject(application)

        application.registerActivityLifecycleCallbacks(activityLifecycleCallback)
    }

    private fun handleActivityCreated(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
    }
}