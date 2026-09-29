package com.codepath.nationalparks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * [RecyclerView.Adapter] that can display a [NationalPark] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class NationalParksRecyclerViewAdapter(
    private val parks: List<NationalPark>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<NationalParksRecyclerViewAdapter.ParkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_national_park, parent, false)
        return ParkViewHolder(view)
    }

    // ViewHolder referencing all views in fragment_national_park.xml
    inner class ParkViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: NationalPark? = null

        val mParkName: TextView = mView.findViewById(R.id.park_name)
        val mParkLocation: TextView = mView.findViewById(R.id.park_location)
        val mParkDescription: TextView = mView.findViewById(R.id.park_description)
        val mParkImage: ImageView = mView.findViewById(R.id.park_image)

        override fun toString(): String {
            return mParkName.text.toString() + " '" + mParkDescription.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        val park = parks[position]

        // Bind text data
        holder.mItem = park
        holder.mParkName.text = park.name
        holder.mParkLocation.text = park.location
        holder.mParkDescription.text = park.description

        // Load image using Glide
        Glide.with(holder.mView)
            .load(park.imageUrl)
            .centerCrop()
            .into(holder.mParkImage)

        // Item click listener
        holder.mView.setOnClickListener {
            holder.mItem?.let { selectedPark ->
                mListener?.onItemClick(selectedPark)
            }
        }
    }

    override fun getItemCount(): Int {
        return parks.size
    }
}