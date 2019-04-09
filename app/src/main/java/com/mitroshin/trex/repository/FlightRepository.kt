package com.mitroshin.trex.repository

import com.mitroshin.trex.network.api.FlightApi

class FlightRepository(private val api: FlightApi) {

    fun fetchFlightList() = api.fetchFlightList()
}