package com.mitroshin.trex.repository

import com.mitroshin.trex.network.api.FlightApi

class FlighRepository(private val api: FlightApi) {

    fun fetchFlightList() = api.fetchFlightList()
}