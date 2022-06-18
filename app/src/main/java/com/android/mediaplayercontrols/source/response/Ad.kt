package com.android.mediaplayercontrols.source.response


/**
 * Each card data is contained in this class
 *
 * @property title
 * @property ctatext - Button text
 * @property ctafb - redirect to browser to show the details
 * @property desc
 * @property price
 * @property salePrice
 * @property cur
 * @property disPer
 * @property x75
 * @property x98 - Currently used this resolution to show in UI
 * @property isSelected - used to highlight one card
 * @property seeAllButton - To display select button in recyclerView
 */
data class Ad(
    var title: String? = null,
    var ctatext: String? = null,
    var ctafb: String? = null,
    var desc: String? = null,
    var price: Int? = null,
    var salePrice: Double? = null,
    var cur: String? = null,
    var disPer: Int? = null,
    var x75: String? = null,
    var x98: String? = null,

    var isSelected:Boolean = false,
    var seeAllButton:Boolean = false
)