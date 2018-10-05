package me.raghu.mvpassignment.presenter

import io.reactivex.Single
import me.raghu.mvpassignment.models.Feed

interface FeedMvp {

    interface View {

        fun updateList(feed: Single<Feed>)
        fun showProgress(boolean: Boolean)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()

        fun fetchData()


    }
}