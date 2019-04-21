package com.mitroshin.trex.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mitroshin.trex.model.tour.Tour
import com.mitroshin.trex.model.tour.TourMapper
import com.mitroshin.trex.network.dto.CompanyListDto
import com.mitroshin.trex.network.dto.FlightListDto
import com.mitroshin.trex.network.dto.HotelListDto
import com.mitroshin.trex.repository.CompanyRepository
import com.mitroshin.trex.repository.FlightRepository
import com.mitroshin.trex.repository.HotelRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FetchTourInteractor @Inject constructor(
    private val hotelRepository: HotelRepository,
    private val flightRepository: FlightRepository,
    private val companyRepository: CompanyRepository,
    private val tourMapper: TourMapper
) {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val mutableResult = MutableLiveData<Result>()

    sealed class Result {
        data class Success(val tourList: List<Tour>) : Result()
        object Error : Result()
    }

    val result: LiveData<Result> = mutableResult

    fun fetchTourList() {
        val handleFunction = Function3<
                HotelListDto,
                FlightListDto,
                CompanyListDto,
                List<Tour>> { hotelList, flightList, companyList ->
            tourMapper.map(hotelList, flightList, companyList)
        }
        val singleZip = Single.zip(
            hotelRepository.fetchHotelList(),
            flightRepository.fetchFlightList(),
            companyRepository.fetchCompanyLisy(),
            handleFunction
        )
        compositeDisposable.add(
            singleZip
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        mutableResult.value = Result.Success(it)
                    },
                    {
                        mutableResult.value = Result.Error
                    }
                )
        )
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }
}