package com.mitroshin.trex.ui.tourList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mitroshin.trex.R
import com.mitroshin.trex.model.tour.Tour

class TourAdapter : RecyclerView.Adapter<TourViewHolder>() {

    private val tourList: MutableList<Tour> = mutableListOf()

    fun setTourList(tourList: List<Tour>) {
        with(this.tourList) {
            clear()
            addAll(tourList)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return tourList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tour, parent, false)
        return TourViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.bind(tourList[position])
    }
}