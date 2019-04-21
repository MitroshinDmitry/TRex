package com.mitroshin.trex.model.company

import com.mitroshin.trex.network.dto.CompanyDto
import javax.inject.Inject

class CompanyMapper @Inject constructor() {

    fun map(companyListDto: List<CompanyDto>): List<Company> {
        return companyListDto.map {
            Company(
                name = it.name
            )
        }
    }
}