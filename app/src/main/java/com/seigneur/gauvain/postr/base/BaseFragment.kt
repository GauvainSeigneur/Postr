package com.seigneur.gauvain.postr.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Base fragment which allows to not duplicate some methods in child
 * Fragment - dedicated to UI not DI
 */
abstract class BaseFragment :Fragment() {

    lateinit var rootview: View


    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract val fragmentLayout: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootview = inflater.inflate(fragmentLayout, container, false)
        onCreateView(rootview, savedInstanceState)
        return rootview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLiveData()
    }

    /**
     * To be overridden
     */
    open fun subscribeToLiveData(){}

    /**
     * method to be overriden.
     * In this method, root view is already inflated and one can use below view getters
     */
    open fun onCreateView(rootView: View, savedInstanceState: Bundle?)  {}

}