package com.mitroshin.trex.di.modules

import com.mitroshin.trex.network.api.CompanyApi
import com.mitroshin.trex.network.api.FlightApi
import com.mitroshin.trex.network.api.HotelApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.myjson.com/bins/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

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