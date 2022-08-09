package com.example.itunesdemoactivity.data

import com.google.gson.annotations.SerializedName

data class ItunesResponse(
    @SerializedName("results")
    val infoList: List<ItunesInfo>
)

data class ItunesInfo(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("artistName")
    val artistName: String,
    @SerializedName("collectionName")
    val collectionName: String,
    @SerializedName("trackName")
    val trackName: String,
    @SerializedName("trackViewUrl")
    val trackViewUrl: String,
    @SerializedName("previewUrl")
    val previewUrl: String
)
