package me.raghu.mvpassignment

import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.network.FetchFeed
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl
import me.raghu.mvpassignment.util.runBlockingSilent
import okhttp3.MediaType
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

    private lateinit var presenterImpl: FeedPresenterImpl

    private  val testScopeProvider = TestScopeProvider()


    @Before
    fun setUP() {
        MockitoAnnotations.initMocks(this)
    }


    @Test
    fun testA() = runBlockingSilent {
        System.out.println("Started Success")
        val feed = Feed()
        feed.title = "About Canada"
        val successResponse = Response.success(feed)
        doReturn(successResponse).`when`(fetchFeed).fetchFeed()
        presenterImpl = FeedPresenterImpl(testScopeProvider, feedView,fetchFeed)
        presenterImpl.fetchData()
        val inOrder = Mockito.inOrder(feedView)
        inOrder.verify(feedView).showProgress(true)
        Mockito.verify(fetchFeed).fetchFeed()
        inOrder.verify(feedView).showProgress(false)
        inOrder.verify(feedView).updateList(successResponse.body()!!)
        System.out.println("Done")

    }

   @Test
    fun testB() = runBlockingSilent {
        System.out.println("Started Error")
        val json = ""
        val failureResponse = Response.error<Feed>(404, ResponseBody.create(MediaType.parse("application/json") ,json))
        doReturn(failureResponse).`when`(fetchFeed).fetchFeed()
        presenterImpl = FeedPresenterImpl(testScopeProvider, feedView,fetchFeed)
        presenterImpl.fetchData()
        val inOrder = Mockito.inOrder(feedView)
        inOrder.verify(feedView).showProgress(true)
        Mockito.verify(fetchFeed).fetchFeed()
        inOrder.verify(feedView).showProgress(false)
        inOrder.verify(feedView).showError("Something went wrong!")
        System.out.println("Done")

    }

}