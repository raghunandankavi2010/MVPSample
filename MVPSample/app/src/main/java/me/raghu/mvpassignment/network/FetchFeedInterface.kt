package me.raghu.mvpassignment.network

import me.raghu.mvpassignment.models.Feed
import retrofit2.Response

interface FetchFeedInterface {

     suspend fun fetchFeed(): Response<Feed>
}