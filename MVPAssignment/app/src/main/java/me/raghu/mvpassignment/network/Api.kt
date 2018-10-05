package me.raghu.mvpassignment.network

import io.reactivex.Single
import me.raghu.mvpassignment.models.Feed
import retrofit2.http.GET
import retrofit2.http.Headers


interface Api {

    @Headers("Content-Type: application/json")
    @GET("bins/11zioh")
    fun getData(): Single<Feed>
}