package com.mitroshin.trex.repository

import com.mitroshin.trex.network.api.CompanyApi

class CompanyRepository(private val api: CompanyApi) {

    fun fetchCompanyLisy() = api.fetchCompanyList()
}