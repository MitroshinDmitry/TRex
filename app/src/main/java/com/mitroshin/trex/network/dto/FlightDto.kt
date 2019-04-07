package com.mitroshin.trex.network.dto

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class FlightListDto(

    @Json(name = "flights")
    val flightList: List<FlightDto>
)


@JsonSerializable
data class FlightDto(

    @Json(name = "id")
    val id: Int,

    @Json(name = "companyId")
    val companyId: Int,

    @Json(name = "departure")
    val departure: String,

    @Json(name = "arrival")
    val arrival: String,

    @Json(name = "price")
    val price: Int
)