package com.mitroshin.trex.model.tour

import com.mitroshin.trex.model.company.Company

data class Tour(
    val hotelName: String = "",
    val countOfFlight: Int = 0,
    val priceForHotel: Int,
    val priceList: List<Price> = emptyList()
) {
    data class Price(
        val company: Company,
        val priceForFlight: Int
    )

    val minPrice: Int
    get() {
        return priceList.minBy {
            it.priceForFlight
        }!!.priceForFlight + priceForHotel
    }
}