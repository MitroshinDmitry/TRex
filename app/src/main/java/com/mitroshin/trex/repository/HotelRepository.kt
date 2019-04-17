package com.mitroshin.trex.repository

import com.mitroshin.trex.network.api.HotelApi
import javax.inject.Inject

class HotelRepository @Inject constructor(
    private val api: HotelApi
) {

    fun fetchHotelList() = api.fetchHotelList()
}