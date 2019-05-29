package me.raghu.mvpassignment.dagger

import dagger.Module
import dagger.Provides
import me.raghu.mvpassignment.network.FetchFeed
import retrofit2.Retrofit


@Module(includes = arrayOf(CoroutineModule::class,NetworkModule::class))
class AppModule {

    @Provides
    fun provideFetchFeed(retrofit: Retrofit): FetchFeed {
        return FetchFeed(retrofit = retrofit)
    }

}
