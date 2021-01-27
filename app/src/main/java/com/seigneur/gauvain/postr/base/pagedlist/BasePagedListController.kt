package com.seigneur.gauvain.postr.base.pagedlist

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.seigneur.gauvain.presentation.common.model.paging.NextRequestStateUiModel

/**
 * EpoxyController which works with PagedLists
 * it displays a loading/error item as last item
 */
abstract class BasePagedListController<T> : PagedListEpoxyController<T>() {

    /**
     * When the value is changed, the controller call requestModelBuild to add or remove the
     * dedicated item (loading/error)
     * see addModels method
     */
    var nextRequestStateUiModel: NextRequestStateUiModel =
        NextRequestStateUiModel.Done //default value
        set(value) {
            field = value
            requestModelBuild()
        }

    abstract fun buildListItem(item: T) : EpoxyModel<*>

    /**
     * Create the EpoxyViewModels
     */
    override fun buildItemModel(currentPosition: Int, item: T?): EpoxyModel<*> {
        item?.let {
            // Movie Item View Model
            return buildListItem(it)

        } ?: run {
            return NextLoadingEpoxyModel_()
                .id(LOADING_ID)
        }
    }

    /**
     * Adding models
     */
    override fun addModels(models: List<EpoxyModel<*>>) {
        when (val currentState = nextRequestStateUiModel) {
            is NextRequestStateUiModel.Done -> super.addModels(models.distinct())
            is NextRequestStateUiModel.Loading -> super.addModels(
                models.plus(
                    //Error View Model
                    NextLoadingEpoxyModel_()
                        .id(LOADING_ID)
                ).distinct()
            )
            is NextRequestStateUiModel.Error -> {
                super.addModels(
                    models.plus(
                        //Error View Model
                        NextErrorEpoxyModel_()
                            .id(ERROR_ID)
                            .errorStr(currentState.data.description)
                            .clickListener { _ -> currentState.data.clickAction() }
                    ).filter { it !is NextLoadingEpoxyModel_ }
                )
            }
        }
    }

    override fun onExceptionSwallowed(exception: RuntimeException) {
        throw exception
    }

    companion object {
        private const val ERROR_ID = "error_id"
        private const val LOADING_ID = "loading_id"
    }
}
