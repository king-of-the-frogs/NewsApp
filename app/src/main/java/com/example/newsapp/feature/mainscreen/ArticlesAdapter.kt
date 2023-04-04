package com.example.newsapp.feature.mainscreen

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArticlesAdapter(val onItemClicked: (Int) -> Unit) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {


    private var articlesData: List<ArticleModel> = emptyList()

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvAuthor: TextView = view.findViewById(R.id.tvAuthor)
        val tvUrl: TextView = view.findViewById(R.id.tvUrl)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            onItemClicked(position)
        }

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = articlesData[position].title
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'  'HH:mm:ss")
        val parsedDate = LocalDateTime.parse(
            articlesData[position].publishedAt,
            DateTimeFormatter.ISO_DATE_TIME
        )
        val formattedDate = parsedDate.format(formatter)
        viewHolder.tvDate.text = formattedDate
        viewHolder.tvAuthor.text = articlesData[position].author
        viewHolder.tvUrl.setOnClickListener {
            val linkUrl = articlesData[position].url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
            val context = viewHolder.itemView.context
            context.startActivity(intent)
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}
