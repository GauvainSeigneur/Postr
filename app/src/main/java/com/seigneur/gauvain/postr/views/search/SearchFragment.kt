package com.seigneur.gauvain.postr.views.search

import android.os.Bundle
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.base.BaseFragment
import com.seigneur.gauvain.presentation.search.SearchViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment: BaseFragment() {

    private val viewModel: SearchViewModel by viewModel()

    lateinit var fragmentAdapter: SearchPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentAdapter = SearchPagerAdapter(childFragmentManager)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        searchButton.setOnClickListener {
            viewModel.updateSearchQuery(searchEditText.text.toString())
        }
    }

    override val fragmentLayout: Int
        get() = R.layout.fragment_search

    private fun setUpViewPager() {
        viewPager.offscreenPageLimit = 3
        viewPager.adapter = fragmentAdapter
        mTabs.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                //mSearchViewModel.currentFragmentPos = position
            }

        })
    }

}