package me.raghu.mvpassignment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.row.view.*
import me.raghu.mvpassignment.models.Row
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import me.raghu.mvpassignment.R.id.description
import me.raghu.mvpassignment.R.id.imageView

class FeedAdapter(private val context: Context, private val items : MutableList<Row> = ArrayList()) : RecyclerView.Adapter<ViewHolder>() {


    init {
        setHasStableIds(true)
    }

    fun addItems(rowList:List<Row>){
        items.addAll(rowList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size


    override fun getItemId(position: Int): Long = position.toLong() // not a good idea. ideally response should have unique id


    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Row) {

        itemView.title.text = item.title
        itemView.description.text = item.description
        if (item.imageHref != null) {

            Log.i("URL",""+item.imageHref)
            GlideApp
                    .with(itemView.context)
                    .load(item.imageHref)
                    .placeholder(object : ColorDrawable(Color.BLACK) {

                    }).transition(withCrossFade())
                    .into(itemView.imageView)
        } else {
            GlideApp
                    .with(itemView.context).clear(itemView.imageView)
        }
    }

}
