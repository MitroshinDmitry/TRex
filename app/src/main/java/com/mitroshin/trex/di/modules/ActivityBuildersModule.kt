package com.mitroshin.trex.di.modules

import com.mitroshin.trex.di.scopes.TourListActivityScope
import com.mitroshin.trex.ui.tourList.TourListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @TourListActivityScope
    @ContributesAndroidInjector(modules = [TourListModule::class])
    abstract fun contributeTourListActivity(): TourListActivity
}