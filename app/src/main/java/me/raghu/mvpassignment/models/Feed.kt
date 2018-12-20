package me.raghu.mvpassignment.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Feed(@Json(name = "title") var title: String = "",
                 @Json(name="rows") var rows: List<Row>? = null)





