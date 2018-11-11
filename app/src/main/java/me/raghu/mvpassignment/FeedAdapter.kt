package me.raghu.mvpassignment

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import kotlinx.android.synthetic.main.row.view.*
import me.raghu.mvpassignment.models.Row
import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup.ItemDetails
import androidx.recyclerview.selection.ItemDetailsLookup





class FeedAdapter(private val context: Context, private val items : MutableList<Row> = ArrayList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var tracker: SelectionTracker<Long>

    init {
        setHasStableIds(true)
    }

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_TEXT_IMAGE = 1
    }

    fun getItems():MutableList<Row> = items

    fun addItems(rowList: List<Row>){
        items.addAll(rowList)
        notifyDataSetChanged()
    }

    fun setSelectionTracker(tracker: SelectionTracker<Long>) {
        this.tracker = tracker
    }
    override fun getItemCount(): Int = items.size


    override fun getItemId(position: Int): Long = position.toLong() // items[position].title!!.hashCode().toLong()

    override fun getItemViewType(position: Int): Int = if (items[position].imageHref != null) TYPE_TEXT_IMAGE else TYPE_TEXT
    
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

            if (tracker.isSelected(adapterPosition.toLong())) {
                itemView.setBackgroundResource(android.R.color.holo_red_dark)
            } else {
                itemView.setBackgroundResource(android.R.color.transparent)
            }
            itemView.title.text = item.title
            itemView.description.text = item.description

            if (item.imageHref != null) {
                GlideApp
                        .with(itemView.context)
                        .load(item.imageHref)
                        .error(R.drawable.error)
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
            if (tracker.isSelected(adapterPosition.toLong())) {
                itemView.setBackgroundResource(android.R.color.holo_red_dark)
            } else {
                itemView.setBackgroundResource(android.R.color.transparent)
            }
        }
    }
}



