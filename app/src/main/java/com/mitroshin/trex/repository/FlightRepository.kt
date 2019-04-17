package com.mitroshin.trex.repository

import com.mitroshin.trex.network.api.FlightApi
import javax.inject.Inject

class FlightRepository @Inject constructor(
    private val api: FlightApi
) {

    fun fetchFlightList() = api.fetchFlightList()
}