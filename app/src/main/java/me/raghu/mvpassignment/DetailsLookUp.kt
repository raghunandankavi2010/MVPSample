package me.raghu.mvpassignment

import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.selection.ItemDetailsLookup

internal class DetailsLookup(private val list: RecyclerView) : ItemDetailsLookup<Long>() {

    internal inner class ItemDetails(private val position: Int,private val title: String) : ItemDetailsLookup.ItemDetails<Long>() {

        override fun getPosition(): Int {
            return position
        }

        override fun getSelectionKey(): Long {
            return title.hashCode().toLong()
        }
    }

    override fun getItemDetails(e: MotionEvent): ItemDetails? {

        val view = list.findChildViewUnder(e.x, e.y)
        if (view != null) {
            val holder = list.getChildViewHolder(view)
            Log.i("DetailsLookUp"," "+holder.itemView.findViewById<TextView>(R.id.title).text.toString())
            return ItemDetails(holder.adapterPosition,holder.itemView.findViewById<TextView>(R.id.title).text.toString())
        }
        return null
    }
}