package com.mitroshin.trex.di.modules

import com.mitroshin.trex.ui.tourList.TourListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ActivityBuildersModule {

    // Todo need to define custom scope for activity
    @Singleton
    @ContributesAndroidInjector(modules = [
        TourListModule::class
    ])
    abstract fun contributeTourListActivity(): TourListActivity
}