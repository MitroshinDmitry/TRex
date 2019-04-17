package com.mitroshin.trex.di.modules

import com.mitroshin.trex.BuildConfig
import com.mitroshin.trex.network.ApplicationJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(
    includes = [
        ApiModule::class
    ]
)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient(BuildConfig.DEBUG))
            .baseUrl("https://api.myjson.com/bins/")
            .addConverterFactory(provideConverterFactory())
            .addCallAdapterFactory(provideAdapterFactory())
            .build()
    }

    private fun provideOkHttpClient(withLoggingInterceptor: Boolean = false): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (withLoggingInterceptor) {
            okHttpClientBuilder.addNetworkInterceptor(provideHttpLoggingInterceptor())
        }
        return okHttpClientBuilder.build()
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    private fun provideConverterFactory(): Converter.Factory {
        val adapterFactory = ApplicationJsonAdapterFactory.INSTANCE
        val moshi = Moshi.Builder()
            .add(adapterFactory)
            .build()
        return MoshiConverterFactory.create(moshi)
    }
}