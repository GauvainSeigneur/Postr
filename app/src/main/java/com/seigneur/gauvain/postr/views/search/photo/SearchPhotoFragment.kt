package com.seigneur.gauvain.postr.views.search.photo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.base.BaseFragment
import com.seigneur.gauvain.postr.views.search.SearchResultController
import com.seigneur.gauvain.presentation.search.SearchViewModel
import com.seigneur.gauvain.presentation.search.photo.SearchPhotoViewModel
import com.seigneur.gauvain.presentation.utils.event.EventObserver
import kotlinx.android.synthetic.main.fragment_search_data.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchPhotoFragment : BaseFragment() {

    private val viewModel: SearchViewModel by sharedViewModel(
        from = { requireParentFragment() }
    )
    private val searchPhotoViewModel: SearchPhotoViewModel by viewModel()
    private val controller = SearchPhotoController()

    override val fragmentLayout: Int
        get() = R.layout.fragment_search_data

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchDataRv.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.VERTICAL,
            false
        )
        searchDataRv.adapter = controller.adapter

        viewModel.searchPhotoQuery.observe(viewLifecycleOwner, EventObserver<String> {
            searchPhotoViewModel.searchPhoto(it)
            // reinitialize liveData
            observePagedList()
        })
        observePagedList()
    }

    private fun observePagedList() {
        searchPhotoViewModel.list?.observe(viewLifecycleOwner, Observer {
            controller.submitList(it)
        })
        searchPhotoViewModel.pagingRequestStateData?.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                controller.pagingRequestUiModel = it
            }
        })
    }

}