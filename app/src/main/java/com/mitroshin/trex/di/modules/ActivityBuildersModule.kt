package com.mitroshin.trex.di.modules

import com.mitroshin.trex.ui.FlightListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeFlightListActivity(): FlightListActivity
}