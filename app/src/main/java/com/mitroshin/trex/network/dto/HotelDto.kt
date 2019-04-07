package com.mitroshin.trex.network.dto

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class HotelListDto(

    @Json(name = "hotels")
    val hotelList: List<HotelDto>
)

@JsonSerializable
data class HotelDto(

    @Json(name = "id")
    val id: Int,

    @Json(name = "flights")
    val flightList: List<Int>,

    @Json(name = "name")
    val name: String,

    @Json(name = "price")
    val price: Int
)