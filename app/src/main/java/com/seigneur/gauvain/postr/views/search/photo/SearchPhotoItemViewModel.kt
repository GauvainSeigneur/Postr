package com.seigneur.gauvain.postr.views.search.photo

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.*
import com.seigneur.gauvain.postr.R

@EpoxyModelClass(layout = R.layout.layout_item_search_user_result)
abstract class SearchPhotoItemViewModel : EpoxyModelWithHolder<SearchPhotoItemViewModel.Holder>() {

    @EpoxyAttribute
    lateinit var userName: String

    override fun bind(holder: Holder) {
        holder.userName?.text = userName
    }

    class Holder : EpoxyHolder() {
        var userName: TextView? = null
        override fun bindView(itemView: View) {
            userName = itemView.findViewById(R.id.itemUserName)
        }
    }
}
