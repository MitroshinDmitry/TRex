package com.mitroshin.trex.network.dto

import com.squareup.moshi.Json

data class HotelResponseDto(
    @Json(name = "success") val success: Boolean,
    @Json(name = "data") val data: HotelListDto
)

data class HotelListDto(

    @Json(name = "hotels")
    val hotelList: List<HotelDto>
)

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