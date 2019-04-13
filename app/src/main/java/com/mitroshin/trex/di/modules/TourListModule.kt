package com.mitroshin.trex.di.modules

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.di.scopes.TourListActivityScope
import com.mitroshin.trex.ui.tourList.TourAdapter
import com.mitroshin.trex.ui.tourList.TourListActivity
import dagger.Module
import dagger.Provides

@Module
class TourListModule {

    @Provides
    @TourListActivityScope
    fun provideRecyclerViewAdapter(): TourAdapter {
        return TourAdapter()
    }

    @Provides
    @TourListActivityScope
    fun provideLayoutManager(activity: TourListActivity): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }
}