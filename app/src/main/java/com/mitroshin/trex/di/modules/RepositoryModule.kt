package com.mitroshin.trex.di.modules

import com.mitroshin.trex.network.api.CompanyApi
import com.mitroshin.trex.network.api.FlightApi
import com.mitroshin.trex.network.api.HotelApi
import com.mitroshin.trex.repository.CompanyRepository
import com.mitroshin.trex.repository.FlightRepository
import com.mitroshin.trex.repository.HotelRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [
    ApiModule::class
])
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCompanyRepository(api: CompanyApi): CompanyRepository {
        return CompanyRepository(api)
    }

    @Provides
    @Singleton
    fun provideFlightRepository(api: FlightApi): FlightRepository {
        return FlightRepository(api)
    }

    @Provides
    @Singleton
    fun provideHotelRepository(api: HotelApi): HotelRepository {
        return HotelRepository(api)
    }
}