package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.presentation.common.mapper.PhotoUiModelMapper
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper
import org.koin.dsl.module

val uiMapperModule = module {
    factory {
        PhotoUiModelMapper(stringProvider = get())
    }
    factory {
        NextRequestUiMapper(stringProvider = get())
    }
    factory {
        SearchUiMapper()
    }
}