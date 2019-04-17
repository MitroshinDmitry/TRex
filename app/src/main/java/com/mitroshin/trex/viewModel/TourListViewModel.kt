package com.mitroshin.trex.viewModel

import androidx.lifecycle.ViewModel
import com.mitroshin.trex.interactor.FetchTourInteractor
import javax.inject.Inject

class TourListViewModel @Inject constructor(
    private val fetchTourInteractor: FetchTourInteractor
) : ViewModel() {

    init {
        fetchTourInteractor.fetchTourListObservable
            .subscribe(this::handleFetchTourResult)
    }

    fun fetchTourList() {
        fetchTourInteractor.fetchTourList()
    }

    private fun handleFetchTourResult(result: FetchTourInteractor.Result) {
        when (result) {
            is FetchTourInteractor.Result.Success -> {

            }
            is FetchTourInteractor.Result.Error -> {

            }
        }
    }
}