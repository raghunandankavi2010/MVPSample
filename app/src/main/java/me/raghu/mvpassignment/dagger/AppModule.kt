package me.raghu.mvpassignment.dagger

import dagger.Module
import dagger.Provides
import me.raghu.mvpassignment.network.FetchFeed
import me.raghu.mvpassignment.util.FeedCache
import retrofit2.Retrofit
import javax.inject.Singleton


@Module(includes = arrayOf(CoroutineModule::class,NetworkModule::class))
class AppModule {

    @Provides
    fun provideFetchFeed(retrofit: Retrofit): FetchFeed {
        return FetchFeed(retrofit = retrofit)
    }

    @Provides
    @Singleton
    fun provideFeedCache(): FeedCache {
        return FeedCache()
    }

}
