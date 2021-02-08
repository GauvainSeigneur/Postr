package com.seigneur.gauvain.postr.views.search.collection

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.base.BaseFragment
import com.seigneur.gauvain.presentation.search.SearchViewModel
import com.seigneur.gauvain.presentation.search.collection.SearchCollectionViewModel
import com.seigneur.gauvain.presentation.utils.event.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchCollectionFragment : BaseFragment() {

    private val viewModel: SearchViewModel by sharedViewModel(
        from = { requireParentFragment() }
    )
    private val searchCollectionViewModel: SearchCollectionViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_search_data

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchCollectionQuery.observe(viewLifecycleOwner, EventObserver {
            Log.d("searchCollection", "query change $it")
            searchCollectionViewModel.searchCollection(it)
            // reinitialize liveData
            observePagedList()
        })
        observePagedList()
    }

    private fun observePagedList() {
        searchCollectionViewModel.list?.observe(viewLifecycleOwner, Observer {
            Log.d("searchCollection", "$it")
        })
        searchCollectionViewModel.pagingRequestStateData?.observe(
            viewLifecycleOwner,
            Observer { state ->
                state?.let {
                    Log.d("searchCollection", "requestState $state")
                }
            })
    }

}