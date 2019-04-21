package com.mitroshin.trex.exceptions

import com.mitroshin.trex.network.dto.FlightDto

sealed class FlightExceptions(message: String): Exception(message) {

    class EmptyFlightList(hotelId: Int): FlightExceptions(
        "Hotel with id:$hotelId not have any flight!"
    )

    class IllegalFlightOnHotel(hotelId: Int, flightIdFromHotel: Int, fullFlightIdList: List<Int>): FlightExceptions(
        "Hotel with id:$hotelId contains illegal flight with id :$flightIdFromHotel \n" +
                "Only next flight ids are available :$fullFlightIdList"
    )

    class IllegalCompanyOfFlight(flightDto: FlightDto, fullCompanyIdList: List<Int>): FlightExceptions(
        "Flight with id:${flightDto.id} contain illegal company with id:${flightDto.companyId} \n" +
                "Only next companies ids are available :$fullCompanyIdList"
    )
}