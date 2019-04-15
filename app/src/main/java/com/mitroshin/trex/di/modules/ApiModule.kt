package com.mitroshin.trex.di.modules

import com.mitroshin.trex.network.api.CompanyApi
import com.mitroshin.trex.network.api.FlightApi
import com.mitroshin.trex.network.api.HotelApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [
    NetworkModule::class
])
class ApiModule {

    @Provides
    @Singleton
    fun provideCompanyApi(retrofit: Retrofit): CompanyApi {
        return retrofit.create(CompanyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightApi(retrofit: Retrofit): FlightApi {
        return retrofit.create(FlightApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelApi(retrofit: Retrofit): HotelApi {
        return retrofit.create(HotelApi::class.java)
    }
}