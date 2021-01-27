package com.seigneur.gauvain.presentation.provider

import android.content.Context
import com.seigneur.gauvain.presentation.R

class StringController(private val context: Context) :
    StringProvider {

    private fun getString(id:Int) = context.getString(id)

    override val noDescriptionProvided: String = getString(R.string.no_description_provided)
}