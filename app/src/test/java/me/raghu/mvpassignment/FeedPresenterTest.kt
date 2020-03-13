package me.raghu.mvpassignment

import kotlinx.coroutines.Job
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.network.FetchFeed
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl
import me.raghu.mvpassignment.util.FeedCache
import me.raghu.mvpassignment.util.runBlockingSilent
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response

class FeedPresenterTest {

    @Mock
    private lateinit var feedView: FeedMvp.View

    @Mock
    private lateinit var fetchFeed: FetchFeed


    private lateinit var feedCache: FeedCache

    private lateinit var presenterImpl: FeedPresenterImpl

    private  val testScopeProvider = TestScopeProvider()

    private lateinit var  job:Job

    @Before
    fun setUP() {
        MockitoAnnotations.initMocks(this)
        feedCache = FeedCache()
        job = Job()
    }


    @Test
    fun testForSuccess() = runBlockingSilent {

        val feed = Feed()
        feed.title = "About Canada"
        val successResponse = Response.success(feed)
        doReturn(successResponse).`when`(fetchFeed).fetchFeed()
        presenterImpl = FeedPresenterImpl(testScopeProvider,fetchFeed,feedCache)
        presenterImpl.attach(feedView)
        presenterImpl.fetchData()
        val inOrder = inOrder(feedView)
        inOrder.verify(feedView).showProgress(true)
        Mockito.verify(fetchFeed).fetchFeed()
        inOrder.verify(feedView).showProgress(false)
        inOrder.verify(feedView).updateList(successResponse.body()!!)

    }

   @Test
    fun testForFailure() = runBlockingSilent {

        val json = ""
        val failureResponse = Response.error<Feed>(404, json.toResponseBody("application/json".toMediaTypeOrNull()))
        doReturn(failureResponse).`when`(fetchFeed).fetchFeed()
        presenterImpl = FeedPresenterImpl(testScopeProvider,fetchFeed,feedCache)
        presenterImpl.attach(feedView)
        presenterImpl.fetchData()
        val inOrder = inOrder(feedView)
        inOrder.verify(feedView).showProgress(true)
        Mockito.verify(fetchFeed).fetchFeed()
        inOrder.verify(feedView).showProgress(false)
        inOrder.verify(feedView).showError("Something went wrong!")

    }

}