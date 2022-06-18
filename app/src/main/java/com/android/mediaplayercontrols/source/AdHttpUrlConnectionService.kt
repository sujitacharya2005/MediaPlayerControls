package com.android.mediaplayercontrols.source

import com.android.mediaplayercontrols.source.response.AdInstance
import com.android.mediaplayercontrols.source.response.Root
import com.android.mediaplayercontrols.source.response._5394
import com.google.gson.Gson
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

private const val BASE_URL = "https://jioads.akamaized.net"
private const val END_POINT = "/test/androidTest/response.json"

/***
 * This class is to fetch json data from a end point
 */
class AdHttpUrlConnectionService() {

    suspend fun getAds() : HashMap<String, AdInstance>? {
        val data = getJson()
        val gson = Gson()
        val root: Root = gson.fromJson(data, Root::class.java)
        return (root.result?.asi?.orf5snmy?.cmps?.dd?.get("5394") as _5394).ads
    }

    private suspend fun getJson() : String {
        val url = URL(BASE_URL + END_POINT)
        val httpClient = url.openConnection() as HttpURLConnection
        if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
            try {
                val stream = BufferedInputStream(httpClient.inputStream)
                return readStream(inputStream = stream)
            } catch (e: Exception) {

            } finally {
                httpClient.disconnect()
            }
        }
        return ""
    }

    private fun readStream(inputStream: BufferedInputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        bufferedReader.forEachLine { stringBuilder.append(it) }
        return stringBuilder.toString()
    }
}


