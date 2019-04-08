package com.mitroshin.trex.network.api

import com.mitroshin.trex.network.dto.HotelResponseDto
import io.reactivex.Single
import retrofit2.http.GET

interface HotelApi {

    @GET("12q3ws")
    fun fetchHotelList(): Single<HotelResponseDto>
}