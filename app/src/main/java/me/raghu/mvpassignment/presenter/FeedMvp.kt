package me.raghu.mvpassignment.presenter

import me.raghu.mvpassignment.FeedActivity
import me.raghu.mvpassignment.models.Feed



interface FeedMvp {

    interface View {

        fun updateList(feed: Feed)

        fun showProgress(boolean: Boolean)

        fun showError(errorMessage: String)

    }

    interface Presenter {

        fun detachView()

        fun fetchData()

        fun attach(feedView: FeedMvp.View)


    }
}