package me.raghu.mvpassignment.network

import kotlinx.coroutines.Deferred
import me.raghu.mvpassignment.models.Feed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface Api {

    @Headers("Content-Type: application/json")
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getData(): Deferred<Response<Feed>>
}