package com.seigneur.gauvain.postr.views.home

import com.airbnb.epoxy.EpoxyModel
import com.seigneur.gauvain.postr.base.pagedlist.BasePagedListController
import com.seigneur.gauvain.presentation.model.PhotoUiModel

/**
 * EpoxyController which works with PagedLists
 */
class PhotoListController : BasePagedListController<PhotoUiModel>() {

    override fun buildListItem(item: PhotoUiModel): EpoxyModel<*> {
        return PhotoItemModel_()
            .id(item.id)
            .title(item.description)
            .description(item.description)
            .thumbnailUrl("lol")
    }
}
