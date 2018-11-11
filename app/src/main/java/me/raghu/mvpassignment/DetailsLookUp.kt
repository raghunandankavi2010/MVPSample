package me.raghu.mvpassignment

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.selection.ItemDetailsLookup

internal class DetailsLookup(private val list: RecyclerView) : ItemDetailsLookup<Long>() {

    internal inner class ItemDetails(private val position: Int) : ItemDetailsLookup.ItemDetails<Long>() {

        override fun getPosition(): Int {
            return position
        }

        override fun getSelectionKey(): Long {
            return position.toLong()
        }
    }

    override fun getItemDetails(e: MotionEvent): ItemDetails? {

        val view = list.findChildViewUnder(e.x, e.y)
        if (view != null) {
            val holder = list.getChildViewHolder(view)
            return ItemDetails(holder.adapterPosition)
        }
        return null
    }
}