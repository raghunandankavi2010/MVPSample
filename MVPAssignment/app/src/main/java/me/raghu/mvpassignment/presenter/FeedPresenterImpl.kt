package me.raghu.mvpassignment.presenter

import android.annotation.SuppressLint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.models.Resource
import me.raghu.mvpassignment.network.FetchFeed


class FeedPresenterImpl: FeedMvp.Presenter {

    private val disposable = CompositeDisposable()
    private var feedView: FeedMvp.View? = null
    private var fetchFeed:FetchFeed = FetchFeed()

    override fun attachView(view: FeedMvp.View) {
        feedView = view

    }

    override fun detachView() {
        feedView = null
        disposable.dispose()
    }

    @SuppressLint("CheckResult")
    override fun fetchData() {

         disposable.add(fetchFeed.fetchFeed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Feed>() {
                    override fun onSuccess(feed: Feed) {
                        feedView?.updateList(Resource.success(feed))

                    }

                    override fun onError(e: Throwable) {
                        feedView?.updateList(Resource.error("Something wrong",null))

                        e.printStackTrace()
                    }
                }))
    }
}