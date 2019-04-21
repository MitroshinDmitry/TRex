package com.mitroshin.trex.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.mitroshin.trex.interactor.FetchTourInteractor
import com.mitroshin.trex.model.tour.Tour
import javax.inject.Inject

class TourListViewModel @Inject constructor(
    private val fetchTourInteractor: FetchTourInteractor
) : ViewModel() {

    private val mediatorState = MediatorLiveData<State>()

    init {
        mediatorState.addSource(fetchTourInteractor.result) {
            this.handleFetchTourResult(it)
        }
    }

    sealed class State {
        object Loading : State()
        object Error : State()
        data class TourList(val data: List<Tour>): State()
    }

    val state: LiveData<State> = mediatorState

    fun fetchTourList() {
        mediatorState.value = State.Loading
        fetchTourInteractor.fetchTourList()
    }

    override fun onCleared() {
        fetchTourInteractor.clearDisposable()
    }

    private fun handleFetchTourResult(result: FetchTourInteractor.Result) {
        when (result) {
            is FetchTourInteractor.Result.Success -> {
                mediatorState.value = State.TourList(result.tourList)
            }
            is FetchTourInteractor.Result.Error -> {
                mediatorState.value = State.Error
            }
        }
    }
}