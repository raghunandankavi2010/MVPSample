package me.raghu.mvpassignment.network


import me.raghu.mvpassignment.models.Feed
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FetchFeed  @Inject
constructor(val retrofit: Retrofit) {



//    init {
//
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BASIC
//
//        val okHttpClient = OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .build()
//
//        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
//
//        retrofit = Retrofit.Builder()
//                .baseUrl("https://dl.dropboxusercontent.com/")
//                .client(okHttpClient)
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                //.addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .build()
//
//    }

     suspend fun fetchFeed(): Response<Feed> = retrofit.create(Api::class.java).getData().await()

}