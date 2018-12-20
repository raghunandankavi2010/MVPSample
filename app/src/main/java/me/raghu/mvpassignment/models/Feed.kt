package me.raghu.mvpassignment.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Feed(@Json(name = "title") var title: String = "",
                 @Json(name="rows") var rows: List<Row>? = null)





