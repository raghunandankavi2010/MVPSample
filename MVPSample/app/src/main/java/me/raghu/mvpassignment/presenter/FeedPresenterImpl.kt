package me.raghu.mvpassignment.presenter

import android.annotation.SuppressLint
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import me.raghu.mvpassignment.models.Result
import me.raghu.mvpassignment.network.FetchFeed
import kotlin.coroutines.experimental.CoroutineContext


class FeedPresenterImpl: FeedMvp.Presenter, CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
    get() = Dispatchers.Main + job

    private val uiScope = CoroutineScope(coroutineContext)

    private var feedView: FeedMvp.View? = null
    private var fetchFeed:FetchFeed = FetchFeed

    override fun attachView(view: FeedMvp.View) {
        feedView = view

    }

    override fun detachView() {
        feedView = null
        job.cancel()
    }



    @SuppressLint("CheckResult")
    override fun fetchData() {

        uiScope.launch {
            try {
               val response = fetchFeed.fetchFeed()
                if(response.isSuccessful) {
                    val feed = response.body()
                    if (feed != null) {
                        feedView?.updateList(Result.Success(feed))
                    }
                }
            } catch (e: Exception) {
                feedView?.updateList(Result.Error(e))
            }
        }

    }
}