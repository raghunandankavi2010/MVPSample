package me.raghu.mvpassignment.presenter

import android.annotation.SuppressLint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import me.raghu.mvpassignment.CoroutineContextProvider
import me.raghu.mvpassignment.network.FetchFeed
import javax.inject.Inject

class FeedPresenterImpl @Inject constructor (var contextPool: CoroutineContextProvider,
                        var fetchFeed:FetchFeed) : FeedMvp.Presenter {
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

      job =  contextPool.uiScope.launch {
            try { 
                mView?.showProgress(true)
                val response = fetchFeed.fetchFeed()
                if(response.isSuccessful) {
                    System.out.println("Success")
                     val feed = response.body()
                     if (feed != null) {
                         mView?.showProgress(false)
                         mView?.updateList(feed)
                     }
                 } else{
                     System.out.println("Failed")
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