package me.raghu.mvpassignment


import android.os.Bundle
import android.support.test.espresso.idling.CountingIdlingResource
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.models.Result
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl


class FeedActivity : AppCompatActivity(),FeedMvp.View {


    private var presenter: FeedMvp.Presenter? = null
    private lateinit  var feedAdapter: FeedAdapter

    fun getIdlingResourceInTest(): CountingIdlingResource {
        return mIdlingRes
    }

    private var mIdlingRes = CountingIdlingResource("FeedActivity")


    override fun updateList(result: Result<Feed>) {
        when (result) {
            is Result.Success -> {
                showProgress(false)
                recyclerView.visibility = View.VISIBLE
                val feed = result.data
                supportActionBar?.title = feed.title
                val filterList = feed.rows!!.filter {  it.title!=null  }
                feedAdapter.addItems(filterList)
            }
            is Result.Error -> {
                result.exception.printStackTrace()
                showProgress(false)
                errorText.visibility = View.VISIBLE
                errorText.text = getString(R.string.error)
            }
        }
    }

/*    override fun <T> updateList(resource: Result<Feed>) {
       if(resource.data==null) {
            showProgress(false)
            errorText.visibility = View.VISIBLE
            errorText.text =getString(R.string.error)
        } else {
            showProgress(false)
            recyclerView.visibility = View.VISIBLE
            val feed = resource.data as Feed
            supportActionBar?.title = feed.title
            val filterList = feed.rows!!.filter {  it.title!=null  }
            feedAdapter.addItems(filterList)
        }
        swipe_container.isRefreshing = false

        mIdlingRes.decrement()

    }*/


    override fun showProgress(boolean: Boolean) {
        if (boolean) {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {

            progressBar.visibility = View.GONE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        feedAdapter = FeedAdapter(this@FeedActivity)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(this@FeedActivity,1))
        recyclerView.layoutManager = LinearLayoutManager(this@FeedActivity)
        recyclerView.adapter = feedAdapter

        swipe_container.setOnRefreshListener {
            // does not do anything as there is no new api to refresh data
            /** Ideally
             * mIdlingRes.increment()
             * presenter?.fetchData()
             * update view and data
            */
            feedAdapter.getItems().clear()
            feedAdapter.notifyDataSetChanged()
            mIdlingRes.increment()
            presenter?.fetchData()
        }


        setSupportActionBar(toolbar)
        attachPresenter()
    }

    private fun attachPresenter() {

        presenter  = FeedPresenterImpl()
        presenter?.attachView(this@FeedActivity)
        mIdlingRes.increment()
        presenter?.fetchData()
        showProgress(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }
}
