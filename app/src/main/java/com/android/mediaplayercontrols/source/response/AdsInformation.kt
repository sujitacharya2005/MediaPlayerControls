package com.android.mediaplayercontrols.source.response


import com.squareup.moshi.Json

/**
 * Json contains many nodes
 * In this file contains each node as a one class
 * */

class _5394 {
    var ads: HashMap<String, AdInstance>? = null
}


class Asi {
    var orf5snmy: Orf5snmy? = null
}

class Cmps {
    var dd: HashMap<String, _5394>? = null
}


class Dd {
    @Json(name = "\"5394\"")
    var fiveThree: _5394? = null
}

class Orf5snmy {
    var cmps: Cmps? = null
}

class Result {
    var asi: Asi? = null
}

class Root {
    var statusCode = 0
    var success = false
    var result: Result? = null
    var errors: ArrayList<Any>? = null
}

