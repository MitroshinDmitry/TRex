package com.mitroshin.trex.network.dto

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CompanyListDto(

    @Json(name = "companies")
    val companyList: List<CompanyDto>
)

@JsonSerializable
data class CompanyDto(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String
)