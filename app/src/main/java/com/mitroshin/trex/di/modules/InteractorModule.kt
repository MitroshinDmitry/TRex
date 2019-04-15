package com.mitroshin.trex.di.modules

import com.mitroshin.trex.interactor.FetchTourInteractor
import com.mitroshin.trex.model.tour.TourMapper
import com.mitroshin.trex.repository.FlightRepository
import com.mitroshin.trex.repository.HotelRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    RepositoryModule::class,
    MapperModule::class
])
class InteractorModule {

    @Provides
    @Singleton
    fun provideFetchTourInteractor(hotelRepository: HotelRepository,
                                   flightRepository: FlightRepository,
                                   tourMapper: TourMapper): FetchTourInteractor {
        return FetchTourInteractor(hotelRepository, flightRepository, tourMapper)
    }
}