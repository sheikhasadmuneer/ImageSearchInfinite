package com.example.testinglibs.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.testinglibs.api.FlickrApi
import com.example.testinglibs.data.FlickrPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrRepository @Inject constructor(private val flickrApi: FlickrApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 25,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FlickrPagingSource(flickrApi, query) }
        ).liveData

}