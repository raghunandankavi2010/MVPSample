package me.raghu.mvpassignment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl

class FeedActivity : AppCompatActivity(),FeedMvp.View {

    private lateinit var presenter: FeedPresenterImpl

    override fun updateList(feed: Single<Feed>) {

    }

    override fun showProgress(boolean: Boolean) {
        if (boolean) {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attachPresenter()
        presenter.fetchData()
    }

    fun attachPresenter() {
        presenter = lastNonConfigurationInstance as FeedPresenterImpl

        if (presenter == null) {
            presenter =  FeedPresenterImpl()
        }
        presenter.attachView(this@FeedActivity)

    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return presenter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
