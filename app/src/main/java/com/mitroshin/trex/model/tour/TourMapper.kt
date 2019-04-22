package com.mitroshin.trex.model.tour

import com.mitroshin.trex.exceptions.FlightExceptions
import com.mitroshin.trex.model.company.Company
import com.mitroshin.trex.network.dto.*
import com.mitroshin.trex.util.FlightListValidator
import javax.inject.Inject

class TourMapper @Inject constructor(
    private val flightListValidator: FlightListValidator
) {

    fun map(hotelListDto: HotelListDto,
            flightListDto: FlightListDto,
            companyListDto: CompanyListDto
    ): List<Tour> {
        return hotelListDto.hotelList.map { hotelDto ->
            val flightListForHotel = getFlightListForHotel(flightListDto, hotelDto)
            Tour(
                hotelName = hotelDto.name,
                countOfFlight = hotelDto.flightList.size,
                priceList = getTourPriceList(flightListForHotel, companyListDto),
                priceForHotel = hotelDto.price
            )
        }
    }

    private fun getFlightListForHotel(flightListDto: FlightListDto, hotelDto: HotelDto): List<FlightDto> {
        return if (flightListValidator.isValid(flightListDto, hotelDto)) {
            flightListDto.flightList.filter {
                hotelDto.flightList.contains(it.id)
            }
        } else {
            throw IllegalStateException("Unknown error !!!")
        }
    }

    private fun getTourPriceList(flightListForHotel: List<FlightDto>,
                                 companyListDto: CompanyListDto): List<Tour.Price> {
        return flightListForHotel.map { flight ->
            Tour.Price(
                company = getCompanyById(flight, companyListDto.companyList),
                priceForFlight = flight.price
            )
        }
    }

    private fun getCompanyById(flight: FlightDto, companyList: List<CompanyDto>): Company {
        return Company(
            name = companyList.find {
                flight.companyId == it.id
            }?.name ?: throw FlightExceptions.IllegalCompanyOfFlight(
                flight,
                companyList
            )
        )
    }
}