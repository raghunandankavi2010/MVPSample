package me.raghu.mvpassignment.dagger

import dagger.Binds
import dagger.Module
import dagger.Provides

import me.raghu.mvpassignment.CoroutineContextProvider
import me.raghu.mvpassignment.FeedActivity
import me.raghu.mvpassignment.network.FetchFeed
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl

@Module
abstract class FeedActivityModule {


    @Binds
    abstract fun provideHomePresenter(presenter: FeedPresenterImpl): FeedMvp.Presenter

  /*  @Provides
    @ActivityScope
    internal fun provideFeedView(feedActivity: FeedActivity): FeedMvp.View {
        return feedActivity
    }

    @Provides
    @ActivityScope
    internal fun provideMainPresenter(coroutineContextProvider: CoroutineContextProvider,fetchFeed: FetchFeed): FeedMvp.Presenter {
        return FeedPresenterImpl(coroutineContextProvider,fetchFeed)
    }*/
}
