package me.raghu.mvpassignment


import androidx.appcompat.view.ActionMode

import androidx.recyclerview.selection.SelectionTracker

class SelectionObserver internal constructor(private val activity: FeedActivity) : SelectionTracker.SelectionObserver<Long>() {
    private var selectedCount = 0
    private var actionMode: ActionMode? = null
    private val callback: ActionModeCallback = ActionModeCallback(activity)

    override fun onItemStateChanged(key: Long, selected: Boolean) {
        if (selected && selectedCount == 0) {
            actionMode = activity.startSupportActionMode(callback)
        }

        if (selected) {
            selectedCount++
        } else {
            selectedCount--
        }

        if (selectedCount == 0) {
            actionMode!!.finish()
        }
    }
}