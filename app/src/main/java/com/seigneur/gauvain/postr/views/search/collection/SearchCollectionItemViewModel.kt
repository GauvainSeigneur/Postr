package com.seigneur.gauvain.postr.views.search.collection

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.*
import com.seigneur.gauvain.postr.R

@EpoxyModelClass(layout = R.layout.layout_item_search_collection)
abstract class SearchCollectionItemViewModel : EpoxyModelWithHolder<SearchCollectionItemViewModel.Holder>() {

    @EpoxyAttribute
    lateinit var collectionName: String

    override fun bind(holder: Holder) {
        holder.collectionNameTextView?.text = collectionName
    }

    class Holder : EpoxyHolder() {
        var collectionNameTextView: TextView? = null
        override fun bindView(itemView: View) {
             collectionNameTextView = itemView.findViewById(R.id.collectionSearchItemName)
        }
    }
}
