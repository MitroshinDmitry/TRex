package com.mitroshin.trex.ui.tourList

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.R
import com.mitroshin.trex.model.tour.Tour
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class TourListActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var tourAdapter: TourAdapter
    @Inject lateinit var layoutManager: RecyclerView.LayoutManager

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(recycler_view) {
            layoutManager = this@TourListActivity.layoutManager
            adapter = tourAdapter
        }

        tourAdapter.setTourList(
            listOf(
                Tour(
                    "Hotel Name 0",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 1",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 2",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 3",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 4",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 5",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 6",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 7",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 8",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 9",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 10",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 11",
                    3,
                    2000
                ),
                Tour(
                    "Hotel Name 12",
                    3,
                    2000
                )
            )
        )
    }
}
