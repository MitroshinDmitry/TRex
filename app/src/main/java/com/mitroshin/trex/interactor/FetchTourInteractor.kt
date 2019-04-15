package com.mitroshin.trex.interactor

import com.mitroshin.trex.model.tour.Tour
import com.mitroshin.trex.model.tour.TourMapper
import com.mitroshin.trex.network.dto.FlightListDto
import com.mitroshin.trex.network.dto.HotelListDto
import com.mitroshin.trex.repository.FlightRepository
import com.mitroshin.trex.repository.HotelRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class FetchTourInteractor(private val hotelRepository: HotelRepository,
                          private val flightRepository: FlightRepository,
                          private val tourMapper: TourMapper,
                          private val compositeDisposable: CompositeDisposable = CompositeDisposable()) {

    sealed class Result {
        data class Success(val tourList: List<Tour>): Result()
        data class Error(val throwable: Throwable): Result()
    }

    private val fetchTourResultSubject: BehaviorSubject<Result> = BehaviorSubject.create()

    fun fetchTourList(): Observable<Result> {
        val handleFunction = BiFunction<HotelListDto, FlightListDto, List<Tour>> { hotelList, flightList ->
            tourMapper.map(hotelList, flightList)
        }
        val singleZip = Single.zip(
            hotelRepository.fetchHotelList(),
            flightRepository.fetchFlightList(),
            handleFunction
        )
        compositeDisposable.add(
            singleZip
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        fetchTourResultSubject.onNext(Result.Success(it))
                    },
                    {
                        fetchTourResultSubject.onNext(Result.Error(it))
                    }
                )
        )
        return fetchTourResultSubject
    }
}