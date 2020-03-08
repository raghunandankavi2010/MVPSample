package me.raghu.mvpassignment.models

import me.raghu.mvpassignment.util.DefaultIfNull

@DefaultIfNull
data class Row( var title: String = "",
                var description: String = "",
                var imageHref: String = "")
