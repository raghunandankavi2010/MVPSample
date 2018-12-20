package me.raghu.mvpassignment.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Row(@Json(name="title")
                var title: String? = null,
                @Json(name="description")
                var description: String? = null,
                @Json(name="imageHref")
                var imageHref: String? = null)
