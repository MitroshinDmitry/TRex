package com.mitroshin.trex.model.tour

import com.mitroshin.trex.exceptions.FlightExceptions
import com.mitroshin.trex.model.company.CompanyMapper
import com.mitroshin.trex.network.dto.*
import com.mitroshin.trex.util.FlightListValidator
import javax.inject.Inject

class TourMapper @Inject constructor(
    private val flightListValidator: FlightListValidator,
    private val companyMapper: CompanyMapper
) {

    fun map(hotelListDto: HotelListDto,
            flightListDto: FlightListDto,
            companyListDto: CompanyListDto
    ): List<Tour> {
        return hotelListDto.hotelList.map { hotelDto ->
            val flightListForHotel = getFlightListForHotel(flightListDto, hotelDto)
            val companyListForHotel = getCompanyListForFlightList(flightListForHotel, companyListDto)
            Tour(
                hotelName = hotelDto.name,
                countOfFlight = hotelDto.flightList.size,
                minPrice = flightListForHotel.minBy {
                    it.price
                }!!.price + hotelDto.price,
                companyList = companyMapper.map(companyListForHotel)
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

    private fun getCompanyListForFlightList(flightList: List<FlightDto>,
                                            companyListDto: CompanyListDto): List<CompanyDto> {
        val fullCompanyIdList = companyListDto.companyList.map {
            it.id
        }
        val resultWithDuplicates = flightList.map { flight ->
            companyListDto.companyList.find {
                it.id == flight.companyId
            } ?: throw FlightExceptions.IllegalCompanyOfFlight(
                flight,
                fullCompanyIdList
            )
        }
        return resultWithDuplicates.distinct()
    }
}