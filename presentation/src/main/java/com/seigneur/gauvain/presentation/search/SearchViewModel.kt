package com.seigneur.gauvain.presentation.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seigneur.gauvain.presentation.utils.event.Event

class SearchViewModel : ViewModel() {

    val searchPhotoQuery        = MutableLiveData<Event<String>>()
    val searchCollectionQuery   = MutableLiveData<Event<String>>()
    val searchUserQuery         = MutableLiveData<Event<String>>()

    fun updateSearchQuery(query: String?) {
        if (!query.isNullOrBlank()) {
            Log.d("search", "updateSearchQuery $query")
            searchPhotoQuery.postValue(Event(query))
            searchCollectionQuery.postValue(Event(query))
            searchUserQuery.postValue(Event(query))
        }
    }

}