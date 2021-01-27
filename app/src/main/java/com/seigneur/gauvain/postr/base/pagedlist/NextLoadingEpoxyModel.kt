package com.seigneur.gauvain.postr.base.pagedlist

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.seigneur.gauvain.postr.R

@EpoxyModelClass(layout = R.layout.layout_loading)
abstract class NextLoadingEpoxyModel : EpoxyModelWithHolder<NextLoadingEpoxyModel.Holder>() {

    class Holder : EpoxyHolder() {
        override fun bindView(itemView: View) {
            // do nothing
        }
    }
}
