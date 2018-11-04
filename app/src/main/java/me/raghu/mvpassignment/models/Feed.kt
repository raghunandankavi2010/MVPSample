package me.raghu.mvpassignment.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Feed( @SerializedName("title") @Expose var title: String = "",
                 @SerializedName("rows") @Expose var rows: List<Row>? = null)





