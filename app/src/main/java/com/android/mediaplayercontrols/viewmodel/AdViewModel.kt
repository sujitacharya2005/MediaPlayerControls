package com.android.mediaplayercontrols.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.mediaplayercontrols.source.response.Ad
import com.android.mediaplayercontrols.viewmodel.usecase.AdListGetUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This ViewModel holds all Ad related data, communicates Fragment This
 * one ViewModel shares data between two fragments AdDetailsFragment and
 * AdFragment
 *
 * @property adListGetUseCase
 */
class AdViewModel(private val adListGetUseCase: AdListGetUseCase) : ViewModel() {
    private val _listOfAdsLiveData = MutableLiveData<List<Ad>>()
    val listOfAdsLiveData: LiveData<List<Ad>>
        get() = _listOfAdsLiveData

    private val _selectedItemLiveData = MutableLiveData<Int>()
    val selectedItemLiveData: LiveData<Int>
        get() = _selectedItemLiveData


    fun getAds() {
        viewModelScope.launch(Dispatchers.IO) {
            _listOfAdsLiveData.postValue(adListGetUseCase.getAds())
        }
    }

    fun itemClicked(item: Ad, position: Int) {
        viewModelScope.launch {
            //previous item is same or not 1st time update
            if (_selectedItemLiveData.value != null) {
                val previousSelectedPosition = _selectedItemLiveData.value!!
                val previousSelectedData =
                    _listOfAdsLiveData.value?.get(_selectedItemLiveData.value!!)
                previousSelectedData?.isSelected = false
                _selectedItemLiveData.value = previousSelectedPosition
            }
            //update current one
            item.isSelected = true
            _selectedItemLiveData.value = position
        }
    }

}