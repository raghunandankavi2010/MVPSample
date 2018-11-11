package me.raghu.mvpassignment;


import androidx.appcompat.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;


public class ActionModeCallback implements ActionMode.Callback {
    private FeedActivity activity;

    ActionModeCallback(FeedActivity activity) {
        this.activity = activity;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        activity.getMenuInflater().inflate(R.menu.context, menu);
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done:
                activity.showSelected();
                break;
        }
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {

    }
}