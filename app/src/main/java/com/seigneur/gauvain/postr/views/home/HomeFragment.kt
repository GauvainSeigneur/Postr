package com.seigneur.gauvain.postr.views.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.base.BaseFragment
import com.seigneur.gauvain.postr.views.home.epoxy.MovieListEpoxyController
import com.seigneur.gauvain.presentation.home.HomeViewModel
import com.seigneur.gauvain.presentation.model.livedata.LiveDataState
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("lolilol", "onViewCreated")

        movies_rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val pagedListController = MovieListEpoxyController()
        movies_rv.adapter = pagedListController.adapter


        viewModel.list?.observe(viewLifecycleOwner, Observer {
            pagedListController.submitList(it)
        })
        /*viewModel.photoListData.observe(viewLifecycleOwner, Observer {
            Log.d("lolilol", "observed $it")
            when (it) {
                is LiveDataState.Success -> {
                    Log.d("lolilol", "photo ${it.data}")
                }
                is LiveDataState.Error -> {
                    Log.d("lolilol", "error ${it.errorData}")
                }
            }
        })*/
    }

}