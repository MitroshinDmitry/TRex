package com.mitroshin.trex.repository

import com.mitroshin.trex.network.api.CompanyApi
import javax.inject.Inject

class CompanyRepository @Inject constructor(
    private val api: CompanyApi
) {

    fun fetchCompanyLisy() = api.fetchCompanyList()
}