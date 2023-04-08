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
import com.example.newsapp.feature.bookmarks.ui.BookmarksAdapter
import com.example.newsapp.feature.domain.ArticleModel
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArticlesAdapter(
    val onItemClicked: (Int) -> Unit,
    private val onBookmarkClick: (ArticleModel) -> Unit
) :
    RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    class ArticleViewHolder(
        itemView: View,
        private val onItemClicked: (Int) -> Unit,
        private val onBookmarkClick: (ArticleModel) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvInfo)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvUrl: TextView = itemView.findViewById(R.id.tvUrl)
        private val ivAddFav: ImageView = itemView.findViewById(R.id.ivAddFav)


        fun bind(articlesData: ArticleModel, position: Int) {

            val formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'  'HH:mm:ss"
            )
            val parsedDate = LocalDateTime.parse(
                articlesData.publishedAt,
                DateTimeFormatter.ISO_DATE_TIME
            )
            val formattedDate = parsedDate.format(formatter)


            tvDate.text = formattedDate
            tvAuthor.text = articlesData.author
            tvTitle.text = articlesData.title

            tvUrl.setOnClickListener {
                val linkUrl = articlesData.url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
                val context = itemView.context
                context.startActivity(intent)
            }

            ivAddFav.setOnClickListener {
                onBookmarkClick.invoke(
                    articlesData
                )
            }

            if (tvTitle.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                tvDate.setTextAppearance(R.style.Subtitle1)
                tvAuthor.setTextAppearance(R.style.Subtitle1)
                tvTitle.setTextAppearance(R.style.Subtitle1)
                tvUrl.setTextAppearance(R.style.Subtitle1)
                ivAddFav.setColorFilter(ContextCompat.getColor(
                        itemView.context, R.color.black_100
                    )
                )
            }

            itemView.setOnClickListener {
                onItemClicked(position)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)

        return ArticleViewHolder(itemView, onItemClicked, onBookmarkClick)
    }

    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleModel = articlesData[position]
        holder.bind(articleModel, position)
    }
}
