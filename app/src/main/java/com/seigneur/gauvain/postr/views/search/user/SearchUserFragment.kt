package com.seigneur.gauvain.postr.views.search.user

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.base.BaseFragment
import com.seigneur.gauvain.presentation.search.SearchViewModel
import com.seigneur.gauvain.presentation.search.user.SearchUserViewModel
import com.seigneur.gauvain.presentation.utils.event.EventObserver
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchUserFragment : BaseFragment() {

    private val viewModel: SearchViewModel by sharedViewModel(
        from = { requireParentFragment() }
    )
    private val searchUserViewModel: SearchUserViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_search_data

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchUserQuery.observe(viewLifecycleOwner, EventObserver<String> {
            Log.d("searchUser", "query change $it")
            searchUserViewModel.searchUser(it)
            // reinitialize liveData
            observePagedList()
        })
        observePagedList()
    }

    private fun observePagedList() {
        searchUserViewModel.list?.observe(viewLifecycleOwner, Observer {
            Log.d("searchUser", "$it")
        })
        searchUserViewModel.pagingRequestStateData?.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                Log.d("searchUser", "requestState $state")
            }
        })
    }

}