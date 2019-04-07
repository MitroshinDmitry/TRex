package com.mitroshin.trex.network.api

import com.mitroshin.trex.network.dto.FlightDto
import io.reactivex.Single
import retrofit2.http.GET

interface FlightApi {

    @GET("zqxvw")
    fun fetchFlightList(): Single<FlightDto>
}