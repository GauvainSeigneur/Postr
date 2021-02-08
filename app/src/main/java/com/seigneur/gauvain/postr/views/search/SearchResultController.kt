package com.seigneur.gauvain.postr.views.search

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
class SearchResultController : BasePagedListController<SearchResultUiModel>() {

    override fun buildListItem(item: SearchResultUiModel): EpoxyModel<*> {
        when (item) {
            is SearchResultUiModel.User -> {
                return SearchUserItemViewModel_()
                    .id(item.id)
                    .userName(item.name)
            }
            is SearchResultUiModel.Photo -> {
                return SearchPhotoItemViewModel_()
                    .id(item.id)
                    .userName(item.id)
            }
            is SearchResultUiModel.Collection -> {
                return SearchCollectionItemViewModel_()
                    .id(item.id)
                    .collectionName(item.id)
            }
        }

    }
}
