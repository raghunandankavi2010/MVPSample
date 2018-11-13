package me.raghu.mvpassignment


import androidx.appcompat.view.ActionMode
import android.view.Menu
import android.view.MenuItem


class ActionModeCallback internal constructor(private val activity: FeedActivity) : ActionMode.Callback {

    override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
        activity.menuInflater.inflate(R.menu.context, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
        return false
    }

    override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.done -> activity.showSelected()
        }
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode) {

    }
}