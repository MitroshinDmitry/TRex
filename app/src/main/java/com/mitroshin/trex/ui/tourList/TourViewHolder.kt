package com.mitroshin.trex.ui.tourList

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.R
import com.mitroshin.trex.model.tour.Tour
import kotlinx.android.synthetic.main.item_tour.view.*

class TourViewHolder(
    itemView: View,
    private val mutableUserAction: MutableLiveData<TourListActivity.UserAction>
    ) : RecyclerView.ViewHolder(itemView) {

    fun bind(tour: Tour) {
        with(itemView) {

            setOnClickListener { mutableUserAction.value = TourListActivity.UserAction.ClickOnTour(tour) }

            hotel_name_text_view.text = tour.hotelName

            count_of_flight_text_view.text = itemView.resources.getQuantityString(
                R.plurals.count_of_flight_variant,
                tour.countOfFlight,
                tour.countOfFlight
            )

            min_price_text_view.text = String.format(
                itemView.resources.getString(R.string.from_min_price_rub),
                tour.minPrice
            )
        }
    }
}