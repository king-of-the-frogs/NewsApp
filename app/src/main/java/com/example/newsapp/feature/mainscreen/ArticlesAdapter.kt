package com.example.newsapp.feature.mainscreen

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArticlesAdapter(
    val onItemClicked: (Int) -> Unit,
    private val onBookmarkClick: (ArticleModel) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

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
        val ivAddFav: Button = view.findViewById(R.id.ivAddFav)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val formatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd'  'HH:mm:ss"
        )
        val parsedDate = LocalDateTime.parse(
            articlesData[position].publishedAt,
            DateTimeFormatter.ISO_DATE_TIME
        )
        val formattedDate = parsedDate.format(formatter)


        viewHolder.tvDate.text = formattedDate
        viewHolder.tvAuthor.text = articlesData[position].author
        viewHolder.tvTitle.text = articlesData[position].title

        if (viewHolder.tvTitle.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            viewHolder.tvDate.setTextAppearance(R.style.Subtitle1)
            viewHolder.tvAuthor.setTextAppearance(R.style.Subtitle1)
            viewHolder.tvTitle.setTextAppearance(R.style.Subtitle1)
            viewHolder.tvUrl.setTextAppearance(R.style.Subtitle1)
                //           viewHolder.ivAddFav.setColorFilter(ContextCompat.getColor(viewHolder.itemView.context, R.color.black_100))
        }

        viewHolder.itemView.setOnClickListener {
            onItemClicked(position)
        }

        viewHolder.tvUrl.setOnClickListener {
            val linkUrl = articlesData[position].url
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
            val context = viewHolder.itemView.context
            context.startActivity(intent)
        }

        viewHolder.ivAddFav.setOnClickListener {
            onBookmarkClick.invoke(
                articlesData[position]
            )
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }
}
