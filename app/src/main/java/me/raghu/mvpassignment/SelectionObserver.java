package me.raghu.mvpassignment;


import androidx.annotation.NonNull;
import androidx.appcompat.view.ActionMode;

import androidx.recyclerview.selection.SelectionTracker;

public class SelectionObserver extends SelectionTracker.SelectionObserver<Long> {
    private int selectedCount = 0;
    private FeedActivity activity;
    private ActionMode actionMode;
    private ActionModeCallback callback;

    SelectionObserver(FeedActivity activity) {
        this.activity = activity;
        callback = new ActionModeCallback(activity);
    }

    @Override
    public void onItemStateChanged(@NonNull Long key, boolean selected) {
        if (selected && selectedCount == 0) {
            actionMode = activity.startSupportActionMode(callback);
        }

        if (selected) {
            selectedCount++;
        } else {
            selectedCount--;
        }

        if (selectedCount == 0) {
            actionMode.finish();
        }
    }
}