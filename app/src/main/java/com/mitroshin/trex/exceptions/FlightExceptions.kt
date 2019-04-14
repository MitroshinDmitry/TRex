package com.mitroshin.trex.exceptions

sealed class FlightExceptions(message: String): Exception(message) {

    class EmptyFlightList(hotelId: Int): FlightExceptions(
        "Hotel with id:$hotelId not have any flight!"
    )

    class IllegalFlightOnHotel(hotelId: Int, flightIdFromHotel: Int, fullFlightIdList: List<Int>): FlightExceptions(
        "Hotel with id:$hotelId contains illegal flight with id :$flightIdFromHotel \n" +
                "Only next flight ids are available :$fullFlightIdList"
    )
}