package com.android.mediaplayercontrols.adapter

import androidx.recyclerview.widget.DiffUtil
import com.android.mediaplayercontrols.source.response.Ad

/**
 * Callback for calculating the diff between two non-null items in a list.
 * */
class DiffCallback : DiffUtil.ItemCallback<Ad>() {
    override fun areItemsTheSame(old: Ad, new: Ad): Boolean {
        return old.title == new.title
    }

    override fun areContentsTheSame(old: Ad, new: Ad): Boolean {
        return old == new
    }
}