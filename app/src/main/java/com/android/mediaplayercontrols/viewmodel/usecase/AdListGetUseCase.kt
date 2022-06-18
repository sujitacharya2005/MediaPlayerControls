package com.android.mediaplayercontrols.viewmodel.usecase

import com.android.mediaplayercontrols.repository.AdRepository
import com.android.mediaplayercontrols.source.response.Ad

/**
 * This cotains one useCase to convert api response class to Ui Data Model i.e list<Ad>
 *
 * @property repository
 */
class AdListGetUseCase(private val repository: AdRepository) {
    suspend fun getAds(): List<Ad> {
        val list = mutableListOf<Ad>()
        val map = repository.getAds()
        if (map != null) {
            for (value in map.values) {
                list.add(value.adObject)
            }
        }
        return list;
    }
}