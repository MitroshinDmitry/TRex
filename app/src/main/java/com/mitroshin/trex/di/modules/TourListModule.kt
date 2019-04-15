package com.mitroshin.trex.di.modules

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.ui.tourList.TourAdapter
import com.mitroshin.trex.ui.tourList.TourListActivity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TourListModule {

    @Provides
    @Singleton
    fun provideRecyclerViewAdapter(): TourAdapter {
        return TourAdapter()
    }

    @Provides
    @Singleton
    fun provideLayoutManager(activity: TourListActivity): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }
}