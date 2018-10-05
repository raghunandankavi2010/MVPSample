package me.raghu.mvpassignment.presenter

import me.raghu.mvpassignment.models.Feed

interface FeedMvp {

    interface View {

        fun updateList(feed: Feed)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()

        fun fetchData()


    }
}