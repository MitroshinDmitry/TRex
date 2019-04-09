package com.mitroshin.trex.network.api

import com.mitroshin.trex.network.dto.CompanyListDto
import io.reactivex.Single
import retrofit2.http.GET

interface CompanyApi {

    @GET("8d024")
    fun fetchCompanyList(): Single<CompanyListDto>
}