package me.raghu.mvpassignment.network

import io.reactivex.Single
import me.raghu.mvpassignment.models.Feed
import retrofit2.Retrofit
import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory


class FetchFeed {

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

    }

    fun fetchFeed():Single<Feed> = retrofit.create(Api::class.java).getData()


}