package com.seigneur.gauvain.postr.views.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.base.BaseFragment
import com.seigneur.gauvain.presentation.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movies_rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val pagedListController = PhotoListController()
        movies_rv.adapter = pagedListController.adapter
        viewModel.list?.observe(viewLifecycleOwner, Observer {
            pagedListController.submitList(it)
        })
        viewModel.nextRequestStateData.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                Log.d("nextRequest", "state $it")
                pagedListController.nextRequestStateUiModel = it
            }
        })
    }

}