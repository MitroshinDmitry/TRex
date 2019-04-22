package com.mitroshin.trex

import com.mitroshin.trex.exceptions.FlightExceptions
import com.mitroshin.trex.model.company.Company
import com.mitroshin.trex.model.tour.Tour
import com.mitroshin.trex.model.tour.TourMapper
import com.mitroshin.trex.network.dto.*
import com.mitroshin.trex.util.FlightListValidator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TourMapperTest {

    private val sut = TourMapper(
        FlightListValidator()
    )

    private val flightListDto = FlightListDto(
        flightList = listOf(
            FlightDto(
                id = 0,
                companyId = 0,
                departure = "time_of_departure",
                arrival = "time_of_arrival",
                price = 100
            ),
            FlightDto(
                id = 1,
                companyId = 1,
                departure = "time_of_departure",
                arrival = "time_of_arrival",
                price = 200
            ),
            FlightDto(
                id = 2,
                companyId = 1,
                departure = "time_of_departure",
                arrival = "time_of_arrival",
                price = 300
            ),
            FlightDto(
                id = 3,
                companyId = 2,
                departure = "time_of_departure",
                arrival = "time_of_arrival",
                price = 400
            ),
            FlightDto(
                id = 4,
                companyId = 0,
                departure = "time_of_departure",
                arrival = "time_of_arrival",
                price = 500
            ),
            FlightDto(
                id = 5,
                companyId = 2,
                departure = "time_of_departure",
                arrival = "time_of_arrival",
                price = 600
            )
        )
    )

    private val hotelListDto = HotelListDto(
        hotelList = listOf(
            HotelDto(
                id = 0,
                flightList = listOf(
                    0, 1, 2
                ),
                name = "hotel_name_0",
                price = 1000
            ),
            HotelDto(
                id = 1,
                flightList = listOf(
                    1, 2, 3, 4
                ),
                name = "hotel_name_1",
                price = 2000
            ),
            HotelDto(
                id = 2,
                flightList = listOf(
                    3, 4
                ),
                name = "hotel_name_2",
                price = 3000
            ),
            HotelDto(
                id = 3,
                flightList = listOf(
                    5
                ),
                name = "hotel_name_3",
                price = 4000
            )
        )
    )

    private val companyListDto = CompanyListDto(
        companyList = listOf(
            CompanyDto(
                id = 0,
                name = "company_name_0"
            ),
            CompanyDto(
                id = 1,
                name = "company_name_1"
            ),
            CompanyDto(
                id = 2,
                name = "company_name_2"
            )
        )
    )

    @Test
    fun should_return_tour_list_with_min_price_for_flight_plus_price_for_hotel() {
        val expectedResultOfMap = listOf(
            Tour(
                hotelName = "hotel_name_0",
                countOfFlight = 3,
                priceForHotel = 1000,
                priceList = listOf(
                    Tour.Price(
                        company = Company(
                            name = "company_name_0"
                        ),
                        priceForFlight = 100
                    ),
                    Tour.Price(
                        company = Company(
                            name = "company_name_1"
                        ),
                        priceForFlight = 200
                    ),
                    Tour.Price(
                        company = Company(
                            name = "company_name_1"
                        ),
                        priceForFlight = 300
                    )
                )
            ),
            Tour(
                hotelName = "hotel_name_1",
                countOfFlight = 4,
                priceForHotel = 2000,
                priceList = listOf(
                    Tour.Price(
                        company = Company(
                            name = "company_name_1"
                        ),
                        priceForFlight = 200
                    ),
                    Tour.Price(
                        company = Company(
                            name = "company_name_1"
                        ),
                        priceForFlight = 300
                    ),
                    Tour.Price(
                        company = Company(
                            name = "company_name_2"
                        ),
                        priceForFlight = 400
                    ),
                    Tour.Price(
                        company = Company(
                            name = "company_name_0"
                        ),
                        priceForFlight = 500
                    )
                )
            ),
            Tour(
                hotelName = "hotel_name_2",
                countOfFlight = 2,
                priceForHotel = 3000,
                priceList = listOf(
                    Tour.Price(
                        company = Company(
                            name = "company_name_2"
                        ),
                        priceForFlight = 400
                    ),
                    Tour.Price(
                        company = Company(
                            name = "company_name_0"
                        ),
                        priceForFlight = 500
                    )
                )
            ),
            Tour(
                hotelName = "hotel_name_3",
                countOfFlight = 1,
                priceForHotel = 4000,
                priceList = listOf(
                    Tour.Price(
                        company = Company(
                            name = "company_name_2"
                        ),
                        priceForFlight = 600
                    )
                )
            )
        )
        val actualResultOfMap = sut.map(hotelListDto, flightListDto, companyListDto)

        Assertions.assertEquals(expectedResultOfMap, actualResultOfMap)

        val expectedMinPriceList = listOf(
            1100, 2200, 3400, 4600
        )

        val actualMinPriceList = actualResultOfMap.map {
            it.minPrice
        }

        Assertions.assertEquals(expectedMinPriceList, actualMinPriceList)
    }

    @Test
    fun should_throw_exception_if_hotel_have_no_flight() {
        val hotelListDto = HotelListDto(
            listOf(
                HotelDto(
                    id = 3,
                    flightList = emptyList(),
                    name = "hotel_without_flights",
                    price = 4000
                )
            )
        )

        Assertions.assertThrows(FlightExceptions.EmptyFlightList::class.java) {
            sut.map(hotelListDto, flightListDto, companyListDto)
        }
    }

    @Test
    fun should_throw_exception_if_hotel_contains_any_one_of_illegal_flight() {
        val hotelListDto = HotelListDto(
            listOf(
                HotelDto(
                    id = 3,
                    flightList = listOf(
                        0, 6
                    ),
                    name = "hotel_without_flights",
                    price = 4000
                )
            )
        )

        Assertions.assertThrows(FlightExceptions.IllegalFlightOnHotel::class.java) {
            sut.map(hotelListDto, flightListDto, companyListDto)
        }
    }

    @Test
    fun should_throw_exception_if_flight_have_illegal_company() {
        val flightListDto = FlightListDto(
            listOf(
                FlightDto(
                    id = 5,
                    companyId = -1,
                    departure = "time_of_departure",
                    arrival = "time_of_arrival",
                    price = 600
                )
            )
        )

        val hotelListDto = HotelListDto(
            listOf(
                HotelDto(
                    id = 3,
                    flightList = listOf(
                        5
                    ),
                    name = "hotel_without_flights",
                    price = 4000
                )
            )
        )

        Assertions.assertThrows(FlightExceptions.IllegalCompanyOfFlight::class.java) {
            sut.map(hotelListDto, flightListDto, companyListDto)
        }
    }
}