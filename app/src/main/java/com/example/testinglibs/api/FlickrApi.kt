package com.example.testinglibs.api

import com.example.testinglibs.models.FlickrPhoto
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {

    companion object {
        const val BASE_URL = "https://api.flickr.com/"
        const val API_KEY = "37ad288835e4c64fc0cb8af3f3a1a65d"
    }


    @GET("services/rest")
    suspend fun searchPhotos(
        @Query("text") text: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,

        @Query("nojsoncallback") nojsoncallback: Int,
        @Query("safe_search") safe_search: Int,
        @Query("format") format: String,
        @Query("method") method: String,
        @Query("api_key") api_key: String
    ): FlickrPhoto
}