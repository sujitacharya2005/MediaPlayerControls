package com.android.mediaplayercontrols.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.mediaplayercontrols.R
import com.android.mediaplayercontrols.fragment.AdAdapterCallback
import com.android.mediaplayercontrols.fragment.ItemClickCallback
import com.android.mediaplayercontrols.source.response.Ad


private object ViewTypes {
    const val SeeAll = 1
    const val Normal = 2
}

/**
 * This is adapter class for recyclerView
 * This recylerview contains two types of items
 * Type - 1 : Ad Card
 * Type - 2 : at end show one select All button
 */
class AdListAdapter : ListAdapter<Ad, RecyclerView.ViewHolder>(DiffCallback()) {

    private lateinit var adAdapterCallback: AdAdapterCallback
    private lateinit var itemClickCallback: ItemClickCallback

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).seeAllButton) ViewTypes.SeeAll else ViewTypes.Normal
    }


    // This ViewHolder is mainly to show "See All" button part of recyclerview
    class SeeAllViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == ViewTypes.Normal) {
            val view = inflater.inflate(R.layout.ad_item_layout, parent, false)
            AdViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.see_all_layout, parent, false)
            SeeAllViewHolder(view)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ViewTypes.SeeAll) {
            holder.itemView.setOnClickListener {
                adAdapterCallback.seeAllButtonClicked()
            }
            return
        }
        (holder as AdViewHolder).bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemClickCallback.itemClicked(getItem(position), holder.absoluteAdapterPosition)
        }
        holder.buyButton.setOnClickListener {
            adAdapterCallback.buyNowButtonClicked(getItem(position))
        }

    }


    fun setSeeAllClickListener(seeAllButtonClick: AdAdapterCallback) {
        this.adAdapterCallback = seeAllButtonClick
    }

    fun setItemClickListener(itemClickCallback: ItemClickCallback) {
        this.itemClickCallback = itemClickCallback
    }

}