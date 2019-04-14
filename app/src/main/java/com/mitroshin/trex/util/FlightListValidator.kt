package com.mitroshin.trex.util

import com.mitroshin.trex.exceptions.FlightExceptions
import com.mitroshin.trex.network.dto.FlightListDto
import com.mitroshin.trex.network.dto.HotelDto

class FlightListValidator {

    fun isValid(flightListDto: FlightListDto, hotelDto: HotelDto): Boolean {
        if (hotelDto.flightList.isEmpty()) {
            throw FlightExceptions.EmptyFlightList(hotelDto.id)
        }
        val fullFlightIdList = flightListDto.flightList.map {
            it.id
        }
        hotelDto.flightList.forEach {
            if (!fullFlightIdList.contains(it)) {
                throw FlightExceptions.IllegalFlightOnHotel(hotelDto.id, it, fullFlightIdList)
            }
        }
        return true
    }
}