package com.mitroshin.trex.ui.tourList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.R
import com.mitroshin.trex.viewModel.TourListViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TourListActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var tourAdapter: TourAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager

    private lateinit var viewModel: TourListViewModel

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tourAdapter = TourAdapter()
        layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TourListViewModel::class.java)
        viewModel.state.observe(this, Observer {
            handleState(it)
        })

        with(recycler_view) {
            layoutManager = this@TourListActivity.layoutManager
            adapter = tourAdapter
        }

        viewModel.fetchTourList()
    }

    private fun handleState(state: TourListViewModel.State) {
        when (state) {
            is TourListViewModel.State.TourList -> {
                tourAdapter.setTourList(state.data)
            }
        }
    }
}
