package me.raghu.mvpassignment

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.row.view.*
import me.raghu.mvpassignment.models.Row


class FeedAdapter(private val context: Context, private val items : MutableList<Row> = mutableListOf()): ListAdapter<Row, RecyclerView.ViewHolder>(FeedDiffCallback) {

    private lateinit var tracker: SelectionTracker<Long>

    init {
        setHasStableIds(true)
    }

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_TEXT_IMAGE = 1
    }

    fun addItems(rowList: List<Row>){
        items.addAll(rowList)
        notifyItemRangeChanged(itemCount,rowList.size)
    }

    fun setSelectionTracker(tracker: SelectionTracker<Long>) {
        this.tracker = tracker
    }
    override fun getItemCount(): Int = items.size


    override fun getItemId(position: Int): Long =  items[position].title.hashCode().toLong()//position.toLong()

    override fun getItemViewType(position: Int): Int = if (items[position].imageHref.isNotEmpty()) TYPE_TEXT_IMAGE else TYPE_TEXT
    
    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            TYPE_TEXT_IMAGE -> ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false))
            // other view holders...
            else -> TextHolder(LayoutInflater.from(context).inflate(R.layout.row_text, parent, false))
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            TYPE_TEXT_IMAGE -> {
                holder as ViewHolder
                holder.bind(items[position])
            }
            TYPE_TEXT -> {
                holder as TextHolder
                holder.bind(items[position])
            }
        }
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Row) {

            if (tracker.isSelected(item.title.hashCode().toLong())) {
                itemView.isActivated = true
                itemView.setBackgroundResource(android.R.color.holo_red_dark)
            } else {
                itemView.isActivated = false
                itemView.setBackgroundResource(android.R.color.transparent)
            }
            itemView.title.setTextFuture(PrecomputedTextCompat.getTextFuture(
                    item.title,
                    TextViewCompat.getTextMetricsParams(itemView.title),
                    /*optional custom executor*/ null))
            itemView.description.setTextFuture(PrecomputedTextCompat.getTextFuture(
                    item.description,
                    TextViewCompat.getTextMetricsParams(itemView.description),
                    /*optional custom executor*/ null))

            if (item.imageHref.isNotEmpty()) {
                GlideApp
                        .with(itemView.context)
                        .load(item.imageHref)
                        .placeholder(R.drawable.error)
                        .into(itemView.imageView)


            } else {
                GlideApp
                        .with(itemView.context).clear(itemView.imageView)

            }
        }
    }

    inner class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Row) {
            itemView.title.text = item.title
            itemView.description.text = item.description
            if (tracker.isSelected(item.title.hashCode().toLong())) {
                itemView.isActivated = true
                itemView.setBackgroundResource(android.R.color.holo_red_dark)
            } else {
                itemView.isActivated = true
                itemView.setBackgroundResource(android.R.color.transparent)
            }
        }
    }
}

private object FeedDiffCallback : DiffUtil.ItemCallback<Row>() {
    override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
        return oldItem == newItem
    }
}


