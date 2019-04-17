package com.mitroshin.trex.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mitroshin.trex.di.ViewModelKey
import com.mitroshin.trex.viewModel.TourListViewModel
import com.mitroshin.trex.viewModel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [
    InteractorModule::class
])
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TourListViewModel::class)
    abstract fun bindsTourListViewModel(viewModel: TourListViewModel): ViewModel
}