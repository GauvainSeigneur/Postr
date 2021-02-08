package com.seigneur.gauvain.postr.views.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.*
import com.seigneur.gauvain.postr.R

@EpoxyModelClass(layout = R.layout.layout_item_photo)
abstract class PhotoItemModel : EpoxyModelWithHolder<PhotoItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String
    @EpoxyAttribute
    lateinit var description: String
    @EpoxyAttribute
    lateinit var thumbnailUrl: String

    override fun bind(holder: Holder) {
        if (title.equals("loading")) {         //todo - remove it
            holder.titleView?.text = title
            holder.descriptionView?.visibility = View.GONE
            holder.thumbnailImageView?.visibility = View.GONE
        } else {
            holder.descriptionView?.visibility = View.VISIBLE
            holder.thumbnailImageView?.visibility = View.VISIBLE
            holder.titleView?.text = title
            holder.descriptionView?.text = description
        }
    }

    class Holder : EpoxyHolder() {
        var titleView: TextView? = null
        var descriptionView: TextView? = null
        var thumbnailImageView: ImageView? = null

        override fun bindView(itemView: View) {
            titleView = itemView.findViewById(R.id.movie_title)
            descriptionView = itemView.findViewById(R.id.movie_description)
            thumbnailImageView = itemView.findViewById(R.id.movie_thumbnail)
        }
    }
}
