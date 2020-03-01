package me.raghu.mvpassignment


import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.espresso.idling.CountingIdlingResource
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import me.raghu.mvpassignment.models.Feed
import me.raghu.mvpassignment.presenter.FeedMvp
import me.raghu.mvpassignment.presenter.FeedPresenterImpl
import javax.inject.Inject


class FeedActivity : AppCompatActivity(), FeedMvp.View {

    private lateinit var feedAdapter: FeedAdapter
    private lateinit var tracker: SelectionTracker<Long>
    private lateinit var actionMode: ActionMode
    private val BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout"
    private lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var presenter: FeedPresenterImpl

    fun getIdlingResourceInTest(): CountingIdlingResource {
        return mIdlingRes
    }

    private var mIdlingRes = CountingIdlingResource("FeedActivity")

    private var savedRecyclerLayoutState: Parcelable? = null


    override fun updateList(feed: Feed) {
        recyclerView.visibility = View.VISIBLE
        supportActionBar?.title = feed.title
        val filterList = feed.rows!!.filter { it.title != null }
        feedAdapter.addItems(filterList)
        if(swipe_container.isRefreshing){
           recyclerView.smoothScrollToPosition(0)
        }else {
            linearLayoutManager.onRestoreInstanceState(savedRecyclerLayoutState)
        }
        swipe_container.isRefreshing = false
        mIdlingRes.decrement()
    }

    override fun showError(errorMessage: String) {
        errorText.visibility = View.VISIBLE
        errorText.text = errorMessage
        swipe_container.isRefreshing = false
        mIdlingRes.decrement()
    }

    override fun showProgress(boolean: Boolean) {
        if (boolean) {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        } else {

            progressBar.visibility = View.GONE
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedRecyclerLayoutState = savedInstanceState?.getParcelable<Parcelable>(BUNDLE_RECYCLER_LAYOUT)
        feedAdapter = FeedAdapter(this@FeedActivity)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(this@FeedActivity, 1))
        linearLayoutManager = LinearLayoutManager(this@FeedActivity)
        recyclerView.layoutManager =  linearLayoutManager
        recyclerView.adapter = feedAdapter

        tracker = SelectionTracker.Builder<Long>(
                "selection-id",
        recyclerView,
         StableIdKeyProvider(recyclerView),
         DetailsLookup(recyclerView),
         StorageStrategy.createLongStorage()
        ).build()

        tracker.addObserver(SelectionObserver(this))
        feedAdapter.setSelectionTracker(tracker)

        swipe_container.setOnRefreshListener {

            mIdlingRes.increment()
            presenter.fetchData()

            tracker = SelectionTracker.Builder<Long>(
                    "selection-id",
                    recyclerView,
                    StableIdKeyProvider(recyclerView),
                    DetailsLookup(recyclerView),
                    StorageStrategy.createLongStorage()
            ).build()

            tracker.addObserver(SelectionObserver(this))
            feedAdapter.setSelectionTracker(tracker)

        }


        setSupportActionBar(toolbar)
        attachPresenter()
    }

    private fun attachPresenter() {

        presenter.attach(this)
        mIdlingRes.increment()
        presenter.fetchData()
        showProgress(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onSupportActionModeStarted(actionMode: ActionMode) {
        super.onSupportActionModeStarted(actionMode)
        this.actionMode = actionMode
    }

   override fun onSupportActionModeFinished(mode: ActionMode) {
        super.onSupportActionModeFinished(mode)
        tracker.clearSelection()
    }

    fun showSelected() {
        val selection = tracker.selection
        val selectedIds = ArrayList<Long>()

        for (id in selection) {
            selectedIds.add(id)
        }
        Toast.makeText(this, selectedIds.toString(), Toast.LENGTH_LONG).show()
        actionMode.finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT,linearLayoutManager.onSaveInstanceState())

    }

}
