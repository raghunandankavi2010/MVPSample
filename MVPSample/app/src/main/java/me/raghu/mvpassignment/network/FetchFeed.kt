package me.raghu.mvpassignment.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.models.Result
import retrofit2.Retrofit
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory


object FetchFeed {

    private var retrofit:Retrofit

    init {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()


        retrofit = Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

    }

    suspend fun fetchFeed(): Response<Feed> = retrofit.create(Api::class.java).getData().await()

}