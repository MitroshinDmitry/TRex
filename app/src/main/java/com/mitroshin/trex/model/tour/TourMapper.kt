package com.mitroshin.trex.model.tour

import com.mitroshin.trex.network.dto.FlightDto
import com.mitroshin.trex.network.dto.FlightListDto
import com.mitroshin.trex.network.dto.HotelDto
import com.mitroshin.trex.network.dto.HotelListDto
import com.mitroshin.trex.util.FlightListValidator
import java.lang.IllegalStateException

class TourMapper(private val flightListValidator: FlightListValidator) {

    fun map(hotelListDto: HotelListDto, flightListDto: FlightListDto): List<Tour> {
        return hotelListDto.hotelList.map { hotelDto ->
            Tour(
                hotelName = hotelDto.name,
                countOfFlight = hotelDto.flightList.size,
                minPrice = getFlightListForHotel(flightListDto, hotelDto).minBy {
                    it.price
                }!!.price + hotelDto.price
            )
        }
    }

    private fun getFlightListForHotel(flightListDto: FlightListDto, hotelDto: HotelDto): List<FlightDto> {

        return if (flightListValidator.isValid(flightListDto, hotelDto)) {
            flightListDto.flightList.filter {
                hotelDto.flightList.contains(it.id)
            }
        } else {
            throw IllegalStateException("Unknown error!!!")
        }
    }
}