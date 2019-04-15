package com.mitroshin.trex.di.modules

import com.mitroshin.trex.util.FlightListValidator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilModule {

    @Provides
    @Singleton
    fun provideFlightListValidator(): FlightListValidator {
        return FlightListValidator()
    }
}