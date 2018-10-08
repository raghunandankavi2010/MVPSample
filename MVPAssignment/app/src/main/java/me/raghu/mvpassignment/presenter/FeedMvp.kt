package me.raghu.mvpassignment.presenter

import me.raghu.mvpassignment.models.Resource


interface FeedMvp {

    interface View {

        fun <T> updateList(feed: Resource<T>)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()

        fun fetchData()

        fun cleaCache()


    }
}