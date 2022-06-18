package com.android.mediaplayercontrols.repository

import com.android.mediaplayercontrols.source.AdHttpUrlConnectionService
import com.android.mediaplayercontrols.source.response.AdInstance

/**
 * This is currently only one thing to communicate remote data
 * TODO when later support for local db for offline view this class can talk local plus remote data
 *
 * @property service
 */
class AdRepository(private val service: AdHttpUrlConnectionService) {
    suspend fun getAds(): HashMap<String, AdInstance>? {
        return service.getAds()
    }
}
