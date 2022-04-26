package com.example.testinglibs.ui.home

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.testinglibs.data.repository.FlickrRepository

class HomeViewModel @ViewModelInject constructor(
    private val repository: FlickrRepository,
    @Assisted state: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY_QUERY = "key_query"
    }

    private val query = state.getLiveData(KEY_QUERY, "")

    //
    val photos = query.switchMap { queryString ->
        repository.getSearchResults(queryString).cachedIn(viewModelScope)
    }


    // search photos
    fun searchPhotos(searchTet: String) {
        query.value = searchTet
    }
}
