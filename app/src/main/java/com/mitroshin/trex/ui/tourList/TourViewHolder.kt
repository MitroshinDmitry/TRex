package com.mitroshin.trex.ui.tourList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.R
import com.mitroshin.trex.model.tour.Tour
import kotlinx.android.synthetic.main.item_tour.view.*

class TourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(data: Tour) {
        with(itemView) {
            hotel_name_text_view.text = data.hotelName
            count_of_flight_text_view.text = data.countOfFlight.toString()
            min_price_text_view.text = String.format(
                itemView.resources.getString(R.string.from_min_price_rub),
                data.minPrice
            )
        }
    }
}