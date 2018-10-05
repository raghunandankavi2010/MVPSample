package me.raghu.mvpassignment


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl
import android.support.test.espresso.idling.CountingIdlingResource
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.view.*


class FeedActivity : AppCompatActivity(),FeedMvp.View {

    private var presenter: FeedPresenterImpl? =null
    private lateinit  var feedAdapter: FeedAdapter

    fun getIdlingResourceInTest(): CountingIdlingResource {
        return mIdlingRes
    }

    var mIdlingRes = CountingIdlingResource("FeedActivity")
    override fun updateList(feed: Feed) {


        if(TextUtils.isEmpty(feed.title)){
            showProgress(false)
            errorText.visibility = View.VISIBLE
            errorText.text ="Something Wrong!.No data to display"
        }else{
            showProgress(false)
            recyclerView.visibility = View.VISIBLE
            supportActionBar?.title = feed.title
            feedAdapter.addItems(feed.rows!!)
        }
        mIdlingRes.decrement()

    }



    fun showProgress(boolean: Boolean) {
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
        recyclerView.layoutManager = LinearLayoutManager(this@FeedActivity)
        recyclerView.adapter = feedAdapter
        setSupportActionBar(toolbar)
        attachPresenter()

    }

    private fun attachPresenter() {
        presenter = lastNonConfigurationInstance as? FeedPresenterImpl

        if (presenter == null) {
            presenter =  FeedPresenterImpl()
            presenter?.attachView(this@FeedActivity)
            mIdlingRes.increment()
            presenter?.fetchData()
            showProgress(true)
        }

    }

    override fun onRetainCustomNonConfigurationInstance(): FeedPresenterImpl? {
        return presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detachView()
    }
}
