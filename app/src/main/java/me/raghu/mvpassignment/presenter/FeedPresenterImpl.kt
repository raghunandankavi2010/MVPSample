package me.raghu.mvpassignment.presenter

import android.annotation.SuppressLint
import android.util.Log
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.raghu.mvpassignment.CoroutineContextProvider
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.network.FetchFeed
import me.raghu.mvpassignment.util.FeedCache
import javax.inject.Inject

class FeedPresenterImpl @Inject constructor(var contextPool: CoroutineContextProvider,
                                            var fetchFeed: FetchFeed, var feedCache: FeedCache) : FeedMvp.Presenter {
    override fun attach(feedView: FeedMvp.View) {
        mView = feedView
    }

    private lateinit var job: Job

    private var mView: FeedMvp.View? = null

    override fun detachView() {
        mView = null
        job.cancel()
    }

    @SuppressLint("CheckResult")
    override fun fetchData() {

        job = contextPool.uiScope.launch {
            val feedCached = feedCache.lru.get("feed")
            if (feedCached != null) {
                mView?.showProgress(false)
                if (feedCached is Feed)
                    mView?.updateList(feedCached)
            } else {
                try {
                    val response = fetchFeed.fetchFeed()
                    if (response.isSuccessful) {
                        Log.i("Feed Fetch", "Success")
                        val feed = response.body()
                        if (feed != null) {
                            feedCache.lru.put("feed", feed)
                            mView?.showProgress(false)
                            mView?.updateList(feed)
                        }
                    } else {
                        Log.i("Feed Fetch", "Failed")
                        mView?.showProgress(false)
                        mView?.showError("Something went wrong!")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    mView?.showProgress(false)
                    mView?.showError("Something went wrong!")
                }
            }
        }
    }
}





