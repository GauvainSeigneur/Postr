package com.seigneur.gauvain.postr.views.home

import android.content.Context
import android.view.View
import com.airbnb.epoxy.*
import com.seigneur.gauvain.postr.R
import com.seigneur.gauvain.postr.utils.loadPhotoUrlWithThumbnail
import com.seigneur.gauvain.postr.widgets.AspectRatioImageView
import com.seigneur.gauvain.presentation.common.model.PhotoUiModel

@EpoxyModelClass(layout = R.layout.layout_item_photo)
abstract class PhotoItemModel : EpoxyModelWithHolder<PhotoItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var thumbnailUrl: String

    @EpoxyAttribute
    lateinit var photoUiModel: PhotoUiModel

    // Holder
    class Holder : EpoxyHolder() {
        lateinit var photoItemImageView: AspectRatioImageView
        var context: Context? = null
        override fun bindView(itemView: View) {
            context = itemView.context
            photoItemImageView = itemView.findViewById(R.id.photoItemImageView)
        }
    }

    override fun bind(holder: Holder) {
        holder.photoItemImageView.setAspectRatio(
            photoUiModel.dimens.width,
            photoUiModel.dimens.height
        )
        holder.photoItemImageView.loadPhotoUrlWithThumbnail(
            photoUiModel.photoUrl.regular,
            photoUiModel.photoUrl.thumb,
            photoUiModel.color
        )
    }
}
