package me.raghu.mvpassignment.presenter

import me.raghu.mvpassignment.models.Feed

interface FeedMvp {

    interface View {

        fun updateList(feed: Feed)
        fun showProgress(boolean: Boolean)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()

        fun fetchData()


    }
}