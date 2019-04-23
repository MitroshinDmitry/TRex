package com.mitroshin.trex.ui.tourList

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.R
import com.mitroshin.trex.model.tour.Tour
import com.mitroshin.trex.viewModel.TourListViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TourListActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: TourListViewModel

    lateinit var tourAdapter: TourAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager

    // Todo create a separate slice for tour list
    private val mutableUserAction = MutableLiveData<UserAction>()

    sealed class UserAction {
        data class ClickOnTour(val tour: Tour): UserAction()
        data class ConfirmTour(val tour: Tour): UserAction()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTourList()
        initViewModel()

        if (savedInstanceState == null) {
            viewModel.fetchTourList()
        }
    }

    private fun initTourList() {
        tourAdapter = TourAdapter(mutableUserAction)
        layoutManager = LinearLayoutManager(this)

        with(recycler_view) {
            layoutManager = this@TourListActivity.layoutManager
            adapter = tourAdapter
        }

        mutableUserAction.observe(this, Observer {
            handleUserAction(it)
        })
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TourListViewModel::class.java)
        viewModel.state.observe(this, Observer {
            handleState(it)
        })
    }

    private fun handleState(state: TourListViewModel.State) {
        when (state) {
            is TourListViewModel.State.TourList -> {
                tourAdapter.setTourList(state.tourList)
                state.previewTour?.let {
                    showTourDialog(it)
                }
            }
            is TourListViewModel.State.Loading -> {
                progress_bar.isVisible = state.isLoading
            }
            is TourListViewModel.State.Error -> {
                Toast.makeText(this, R.string.network_error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleUserAction(action: UserAction) {
        when (action) {
            is UserAction.ClickOnTour -> {
                viewModel.pickCompany(action.tour)
            }
            is UserAction.ConfirmTour -> {
                Toast.makeText(this, "Price : ${action.tour.fullPrice}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun showTourDialog(tour: Tour) {
        val sortedPriceList = tour.minSortedPriceList
        val sortedCompanyList = sortedPriceList.map {
            it.company.name + " : " + (it.priceForFlight + tour.priceForHotel)
        }
        val itemClickListener = DialogInterface.OnClickListener { _, _ -> }
        val positiveClickListener = DialogInterface.OnClickListener { dialog, _ ->
            val checkedPosition = (dialog as AlertDialog).listView.checkedItemPosition
            mutableUserAction.value = UserAction.ConfirmTour(
                tour.copy(
                    chosenFlightPrice = sortedPriceList[checkedPosition]
                )
            )
        }
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder
            .setTitle(getString(R.string.choose_company))
            .setSingleChoiceItems(
                sortedCompanyList.toTypedArray(),
                0,
                itemClickListener
            )
            .setPositiveButton(
                getString(R.string.confirm),
                positiveClickListener
            )
            .setOnDismissListener {
                viewModel.clearPreviewTour()
            }
            .create()
            .show()
    }
}
