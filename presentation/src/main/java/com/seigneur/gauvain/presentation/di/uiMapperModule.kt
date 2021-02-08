package com.seigneur.gauvain.presentation.di

import com.seigneur.gauvain.presentation.home.HomeUiMapper
import com.seigneur.gauvain.presentation.common.pagedlist.NextRequestUiMapper
import com.seigneur.gauvain.presentation.search.SearchUiMapper
import org.koin.dsl.module

val uiMapperModule = module {
    factory {
        HomeUiMapper(stringProvider = get())
    }
    factory {
        NextRequestUiMapper(stringProvider = get())
    }
    factory {
        SearchUiMapper()
    }
}