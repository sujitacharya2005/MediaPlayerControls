package com.android.mediaplayercontrols.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.mediaplayercontrols.viewmodel.usecase.AdListGetUseCase

/**
 * This factory class used by ViewModelProvider
 * When viewModel contains any parameter that should pass factory and
 * factory will get that viewmodel instance
 *
 * @property adListGetUseCase
 */
class AdViewModelFactory(private val adListGetUseCase: AdListGetUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AdViewModel(adListGetUseCase) as T
    }
}