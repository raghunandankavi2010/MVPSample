package me.raghu.mvpassignment.network

import kotlinx.coroutines.flow.Flow
import me.raghu.mvpassignment.models.Feed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface Api {

    @Headers("Content-Type: application/json")
    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getData(): Response<Feed>
}