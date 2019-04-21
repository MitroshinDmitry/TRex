package com.mitroshin.trex.model.tour

import com.mitroshin.trex.model.company.Company

data class Tour(
    val hotelName: String = "",
    val countOfFlight: Int = 0,
    val minPrice: Int = 0,
    // Maybe set will be better
    val companyList: List<Company> = emptyList(),
    val company: Company? = null
)