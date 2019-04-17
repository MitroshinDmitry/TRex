package com.mitroshin.trex.di.modules

import com.mitroshin.trex.ui.tourList.TourListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [
    ViewModelModule::class
])
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeTourListActivity(): TourListActivity
}