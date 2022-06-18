package com.android.mediaplayercontrols.adapter

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.mediaplayercontrols.R
import com.android.mediaplayercontrols.source.response.Ad
import com.bumptech.glide.Glide

/**
 * This ViewHolder describes an Ad Card details
 *
 * @property view
 */
class AdViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val textView = view.findViewById<TextView>(R.id.text)
    private val priceTextView = view.findViewById<TextView>(R.id.price)
    private val imageView = view.findViewById<ImageView>(R.id.image)
    val buyButton = view.findViewById<TextView>(R.id.buyButton)

    fun bind(item: Ad?) {
        if (item != null) {
            Glide.with(itemView.context)
                .load(item.x98)
                .into(imageView)
        }
        textView.text = item?.title
        val salePrice = "Rs.${item?.salePrice}"
        val price = "Rs.${item?.price}"
        val discount = "(${item?.disPer}% off)"
        val text = SpannableString("$salePrice $price $discount")

        text.setSpan(RelativeSizeSpan(1.1f),
            0,
            salePrice.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(StyleSpan(Typeface.BOLD),
            0,
            salePrice.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text.setSpan(StrikethroughSpan(),
            salePrice.length + 1,
            text.length - discount.length - 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        priceTextView.text = text;
        buyButton.text = item?.ctatext;
        buyButton.isSelected = item?.isSelected == true
        view.isSelected = item?.isSelected == true
    }

}