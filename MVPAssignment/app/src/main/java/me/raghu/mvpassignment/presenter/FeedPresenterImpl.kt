package me.raghu.mvpassignment.presenter


class FeedPresenterImpl: FeedMvp.Presenter {

    private lateinit var feedView: FeedMvp.View
    override fun attachView(view: FeedMvp.View) {
        feedView = view

    }

    override fun detachView() {
        feedView = null
    }

    override fun fetchData() {
     feedView.showProgress(true)


    }
}