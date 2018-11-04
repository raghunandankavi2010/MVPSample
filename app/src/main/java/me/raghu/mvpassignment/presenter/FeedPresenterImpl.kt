package me.raghu.mvpassignment.presenter

import android.annotation.SuppressLint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.raghu.mvpassignment.CoroutineContextProvider
import me.raghu.mvpassignment.network.FetchFeed

class FeedPresenterImpl(var contextPool: CoroutineContextProvider,
                        var feedView: FeedMvp.View,
                        var fetchFeed:FetchFeed) : FeedMvp.Presenter {

    private lateinit var job: Job

    private var mView: FeedMvp.View? = feedView

    override fun detachView() {
        mView = null
        job.cancel()
    }

    @SuppressLint("CheckResult")
    override fun fetchData() {

      job =  contextPool.uiScope.launch {
            try {
                feedView.showProgress(true)
               val response = fetchFeed.fetchFeed()
                if(response.isSuccessful) {
                    val feed = response.body()
                    if (feed != null) {
                        feedView.showProgress(false)
                        mView?.updateList(feed)
                    }
                } else{
                    feedView.showProgress(false)
                    mView?.showError("Something went wrong!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                feedView.showProgress(false)
                mView?.showError("Something went wrong!")
            }
        }
    }
}