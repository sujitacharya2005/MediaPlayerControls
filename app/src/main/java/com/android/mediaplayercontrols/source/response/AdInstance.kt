package com.android.mediaplayercontrols.source.response

import org.json.JSONObject


/**
 * There are many ad information
 * e.g.
 * "ajio_460344571_maroon": {ad : {...}}
 * "ajio_460344572_maroon": {ad : {...}}
 *  each string map to one AdInstance
 * */
class AdInstance {
    var ad: String? = null

    val adObject : Ad
        get() {
            val json = JSONObject(ad)
            val title = json.getString("title")
            val ctatext = json.getString("ctatext")
            val ctafb = json.getString("ctafb")
            val desc = json.getString("desc")
            val price = json.getInt("price")
            val salePrice = json.getDouble("salePrice")
            val cur = json.getString("cur")
            val disPer = json.getInt("disPer")
            val customimage = json.getJSONArray("customimage")
            val x75 = (customimage[0] as JSONObject).getString("60x75")
            val x98 = (customimage[0] as JSONObject).getString("78x98")
            return Ad(title, ctatext, ctafb, desc, price, salePrice, cur, disPer, x75, x98 )
        }
}