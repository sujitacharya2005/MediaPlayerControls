package com.android.mediaplayercontrols.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.mediaplayercontrols.R
import com.android.mediaplayercontrols.adapter.AdListAdapter
import com.android.mediaplayercontrols.repository.AdRepository
import com.android.mediaplayercontrols.source.response.Ad
import com.android.mediaplayercontrols.source.AdHttpUrlConnectionService
import com.android.mediaplayercontrols.viewmodel.AdViewModel
import com.android.mediaplayercontrols.viewmodel.AdViewModelFactory
import com.android.mediaplayercontrols.viewmodel.usecase.AdListGetUseCase

/**
 * This is Details fragment will display as top Fragment when click on "See All" button
 * */
class AdDetailsFragment : Fragment(), ItemClickCallback {
    lateinit var adViewModel: AdViewModel
    lateinit var adapter: AdListAdapter
    lateinit var seeAllButtonClick: AdAdapterCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        seeAllButtonClick = context as AdAdapterCallback
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ad, container, false)
        initRecyclerView(view)
        initializeViewModel()
        observeAdsLiveData()
        observeSelectedItemLiveData()
        return view
    }

    private fun observeSelectedItemLiveData() {
        adViewModel.selectedItemLiveData.observe(viewLifecycleOwner, {
            adapter.notifyItemChanged(it)
        })
    }

    private fun initializeViewModel() {
        val repository = AdRepository(AdHttpUrlConnectionService())
        val adListGetUseCase = AdListGetUseCase(repository)
        ViewModelProvider(requireActivity(), AdViewModelFactory(adListGetUseCase))
            .get(AdViewModel::class.java)
            .apply { adViewModel = this }
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        adapter = AdListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun observeAdsLiveData() {
        adViewModel.listOfAdsLiveData.observe(viewLifecycleOwner, {
            adapter.setSeeAllClickListener(seeAllButtonClick)
            adapter.setItemClickListener(this)
            adapter.submitList(it)
        })
    }


    override fun itemClicked(item: Ad, position: Int) {
        adViewModel.itemClicked(item, position)
    }
}