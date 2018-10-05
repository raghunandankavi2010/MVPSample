package me.raghu.mvpassignment.presenter


class FeedPresenterImpl: FeedMvp.Presenter {

    private var feedView: FeedMvp.View? = null

    override fun attachView(view: FeedMvp.View) {
        feedView = view

    }

    override fun detachView() {
        feedView = null
    }

    override fun fetchData() {
     feedView?.showProgress(true)


    }
}