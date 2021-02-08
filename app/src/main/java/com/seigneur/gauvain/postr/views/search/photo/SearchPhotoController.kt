package com.seigneur.gauvain.postr.views.search.photo

import com.airbnb.epoxy.EpoxyModel
import com.seigneur.gauvain.postr.base.pagedlist.BasePagedListController
import com.seigneur.gauvain.postr.views.search.collection.SearchCollectionItemViewModel_
import com.seigneur.gauvain.postr.views.search.photo.SearchPhotoItemViewModel
import com.seigneur.gauvain.postr.views.search.photo.SearchPhotoItemViewModel_
import com.seigneur.gauvain.postr.views.search.user.SearchUserItemViewModel_
import com.seigneur.gauvain.presentation.common.model.SearchResultUiModel

/**
 * EpoxyController which works with PagedLists
 */
class SearchPhotoController : BasePagedListController<SearchResultUiModel.Photo>() {

    override fun buildListItem(item: SearchResultUiModel.Photo): EpoxyModel<*> {
        return SearchPhotoItemViewModel_()
            .id(item.id)
            .userName(item.id)
    }

}
