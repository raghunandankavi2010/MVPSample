package me.raghu.mvpassignment

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.network.FetchFeed
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Response
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class FeedPresenterTest {

    @Mock
    private lateinit var feedView: FeedMvp.View

    @Mock
    private lateinit var fetchFeed: FetchFeed

    private lateinit var presenterImpl: FeedPresenterImpl

    private fun <T> runBlockingSilent(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope.() -> T) {
        runBlocking(context, block)
    }

    @Before
    fun setUP() {
        MockitoAnnotations.initMocks(this)
        val testScopeProvider = TestScopeProvider()
        presenterImpl = FeedPresenterImpl(testScopeProvider, feedView,fetchFeed)
    }

   @Test
    fun testSuccess() = runBlockingSilent {
        
        val feed = Feed()
        feed.title = "About Canada"
        val successResponse = Response.success(feed)
        doReturn(successResponse).`when`(fetchFeed).fetchFeed()
        Mockito.`when`(fetchFeed.fetchFeed()).thenReturn(successResponse)
        presenterImpl.fetchData()
        val inOrder = Mockito.inOrder(feedView)
        inOrder.verify(feedView).showProgress(true)
        inOrder.verify(feedView).showProgress(false)
        Mockito.verify(feedView).updateList(successResponse.body()!!)

    }

 /*   @Test
    fun testError() = runBlockingSilent {

        val json = ""
        val failureResponse = Response.error<Feed>(404, ResponseBody.create(MediaType.parse("application/json") ,json))
        doReturn(failureResponse).`when`(fetchFeed).fetchFeed()
        presenterImpl.fetchData()
        val inOrder = Mockito.inOrder(feedView)
        inOrder.verify(feedView).showProgress(true)
        inOrder.verify(feedView).showProgress(false)
        Mockito.verify(feedView).showError("Something went wrong!")

    }
*/
}