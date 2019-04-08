package com.mitroshin.trex.network.dto

import com.squareup.moshi.Json

data class CompanyResponseDto(
    @Json(name = "success") val success: Boolean,
    @Json(name = "data") val data: CompanyListDto
)

data class CompanyListDto(

    @Json(name = "companies")
    val companyList: List<CompanyDto>
)

data class CompanyDto(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
)