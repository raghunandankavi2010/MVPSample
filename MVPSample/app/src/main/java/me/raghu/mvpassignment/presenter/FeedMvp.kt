package me.raghu.mvpassignment.presenter

import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.models.Result


interface FeedMvp {

    interface View {

        fun  updateList(feed: Result<Feed>)

        fun showProgress(boolean: Boolean)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()

        fun fetchData()

    }
}