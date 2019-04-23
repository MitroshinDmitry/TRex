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
        data class Loading(val isLoading: Boolean) : State()
        data class TourList(val tourList: List<Tour>,
                            val previewTour: Tour? = null): State()
        object Error : State()
    }

    val state: LiveData<State> = mediatorState

    fun fetchTourList() {
        mediatorState.value = State.Loading(true)
        fetchTourInteractor.fetchTourList()
    }

    fun pickCompany(tour: Tour) {
        updateTourListState(tour)
    }

    fun clearPreviewTour() {
        updateTourListState(null)
    }

    override fun onCleared() {
        fetchTourInteractor.clearDisposable()
    }

    private fun updateTourListState(tour: Tour?) {
        val currentValue = mediatorState.value as State.TourList
        mediatorState.value = currentValue.copy(
            previewTour = tour
        )
    }

    private fun handleFetchTourResult(result: FetchTourInteractor.Result) {
        mediatorState.value = State.Loading(false)
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