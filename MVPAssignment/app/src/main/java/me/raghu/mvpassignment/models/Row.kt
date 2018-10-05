package me.raghu.mvpassignment.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Row(@SerializedName("title")
                @Expose
                var title: String? = null,
                @SerializedName("description")
                @Expose
                var description: String? = null,
                @SerializedName("imageHref")
                @Expose
                var imageHref: Any? = null)
