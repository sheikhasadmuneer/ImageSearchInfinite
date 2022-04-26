package com.example.testinglibs.data

import androidx.paging.PagingSource
import com.example.testinglibs.api.FlickrApi
import com.example.testinglibs.models.FlickrPhoto
import retrofit2.HttpException
import java.io.IOException


private const val START_INDEX = 1

class FlickrPagingSource(
    private val flickrApi: FlickrApi,
    private val query: String
) : PagingSource<Int, FlickrPhoto.PhotoItem>() {


    // fetches data sequentially..
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FlickrPhoto.PhotoItem> {
        val position = params.key ?: START_INDEX

        return try {

            val response = flickrApi.searchPhotos(
                query,
                position,
                params.loadSize,
                1,
                1,
                "json",
                "flickr.photos.search",
                FlickrApi.API_KEY
            )

            val photos = response.photos

            LoadResult.Page(
                data = photos.photo,
                prevKey = if (position == START_INDEX) null else position - 1,
                nextKey = if (photos.photo.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}