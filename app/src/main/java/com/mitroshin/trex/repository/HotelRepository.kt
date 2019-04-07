package com.mitroshin.trex.repository

import com.mitroshin.trex.network.api.HotelApi

class HotelRepository(private val api: HotelApi) {

    fun fetchHotelList() = api.fetchHotelList()
}