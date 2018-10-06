package me.raghu.mvpassignment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.row.view.*
import me.raghu.mvpassignment.models.Row


class FeedAdapter(private val context: Context, private val items : MutableList<Row> = ArrayList()) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    init {
        setHasStableIds(true)
    }

    companion object {
        const val TYPE_TEXT = 0
        const val TYPE_TEXT_IMAGE = 1
    }


    fun addItems(rowList: List<Row>){
        items.addAll(rowList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size


    override fun getItemId(position: Int): Long = items[position].title!!.hashCode().toLong()

    override fun getItemViewType(position: Int): Int {
        return if (items[position].imageHref != null) TYPE_TEXT_IMAGE else TYPE_TEXT
    }


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
}


class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Row) {

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

class TextHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Row) {
        itemView.title.text = item.title
        itemView.description.text = item.description
    }
}
