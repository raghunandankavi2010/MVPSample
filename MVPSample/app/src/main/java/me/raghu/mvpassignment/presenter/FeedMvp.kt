package me.raghu.mvpassignment.presenter

import me.raghu.mvpassignment.models.Resource


interface FeedMvp {

    interface View {

        fun <T> updateList(feed: Resource<T>)

        fun showProgress(boolean: Boolean)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()

        fun fetchData()

    }
}