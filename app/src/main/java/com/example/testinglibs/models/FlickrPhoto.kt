package com.example.testinglibs.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize



@Parcelize
data class FlickrPhoto(
    val photos: Photos
) : Parcelable {

    @Parcelize
    data class Photos(
        val photo: List<PhotoItem>
    ) : Parcelable

    @Parcelize
    data class PhotoItem(
        val server: String = "",
        val farm: Int = 0,
        val id: String = "",
        val secret: String = "",
        val title: String = ""
    ) : Parcelable {
        val attributionUrl get() = "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg"
    }
//    (https://farm](https//farm/){farm}.static.flickr.com/{server}/{id}_{secret}.jpg).

}



