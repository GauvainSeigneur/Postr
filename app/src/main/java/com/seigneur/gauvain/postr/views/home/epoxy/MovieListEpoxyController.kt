package com.seigneur.gauvain.postr.views.home.epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.seigneur.gauvain.presentation.model.PhotoUiModel

/**
 * EpoxyController which works with PagedLists
 */
class MovieListEpoxyController : PagedListEpoxyController<PhotoUiModel>() {

    private var isError: Boolean = false

    var error: String? = ""
        set(value) {
            field = value?.let {
                isError = true
                it
            } ?: run {
                isError = false
                null
            }
            if (isError) {
                requestModelBuild()
            }
        }

    var isLoading = false
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }


    /**
     * Create the EpoxyViewModels
     */
    override fun buildItemModel(currentPosition: Int, item: PhotoUiModel?): EpoxyModel<*> {
        item?.let {
            //Movie Item View Model
            return MovieItemModel_()
                .id(item.id)
                .title(item.description ?: "Unknown")
                .description(item.description ?: "Uknown")
                .thumbnailUrl("lol")

        } ?: run {
            //Loading View Model
            return LoadingEpoxyModel_()
                .id("loading")
        }
    }

    /**
     * Adding models
     */
    override fun addModels(models: List<EpoxyModel<*>>) {
        if (isError) {
            super.addModels(
                models.plus(
                    //Error View Model
                    ErrorEpoxyModel_()
                        .id("Error")
                        .errorStr(error)
                ).filter { !(it is LoadingEpoxyModel_) }
            )
        } else if (isLoading) {
            super.addModels(
                models.plus(
                    //Error View Model
                    LoadingEpoxyModel_()
                        .id("loading")
                ).distinct()
            )
        } else {
            super.addModels(models.distinct())
        }
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {

    }
}
