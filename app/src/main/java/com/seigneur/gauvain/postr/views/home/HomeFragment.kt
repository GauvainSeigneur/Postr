package com.seigneur.gauvain.postr.views.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.base.BaseFragment
import com.seigneur.gauvain.postr.views.MainActivity
import com.seigneur.gauvain.postr.views.login.LogInActivity
import com.seigneur.gauvain.presentation.HomeViewModel
import com.seigneur.gauvain.presentation.SplashViewModel
import com.seigneur.gauvain.presentation.model.AuthenticationState
import com.seigneur.gauvain.presentation.model.livedata.LiveDataState
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override val fragmentLayout: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("lolilol", "onViewCreated")
        viewModel.photoListData.observe(viewLifecycleOwner, Observer {
            Log.d("lolilol", "observed $it")
            when (it) {
                is LiveDataState.Success -> {
                    Log.d("lolilol", "photo ${it.data}")
                }
                is LiveDataState.Error -> {
                    Log.d("lolilol", "error ${it.errorData}")
                }
            }
        })
    }

}