package com.mitroshin.trex.di.modules

import com.mitroshin.trex.model.tour.TourMapper
import com.mitroshin.trex.util.FlightListValidator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    UtilModule::class
])
class MapperModule {

    @Provides
    @Singleton
    fun provideTourMapper(validator: FlightListValidator): TourMapper {
        return TourMapper(validator)
    }
}