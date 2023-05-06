package com.example.newsapp.feature.worldnews

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WorldNewsAdapter(
    private val onBookmarkClick: (ArticleModel) -> Unit,
    private val onFullClick: (ArticleModel) -> Unit,

    ) :
    RecyclerView.Adapter<WorldNewsAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()

    class ViewHolder(
        itemView: View,
        private val onBookmarkClick: (ArticleModel) -> Unit,
        private val onFullClick: (ArticleModel) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvUrl: TextView = itemView.findViewById(R.id.tvUrl)
        private val ivAddFav: ImageView = itemView.findViewById(R.id.ivAddFav)
        private val ivFull: ImageView = itemView.findViewById(R.id.ivFull)
        private val background: ImageView = itemView.findViewById(R.id.ivBackground)

        fun bind(articlesData: ArticleModel) {

            val formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'  'HH:mm"
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
                ivAddFav.setImageResource(R.drawable.ic_baseline_star_24)
            }

            ivFull.setOnClickListener {
                onFullClick.invoke(
                    articlesData
                )
            }

            if (background.context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
            ) {
                background.setImageResource(R.drawable.form_full)
            }
        }
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(
            viewGroup.context
        ).inflate(
            R.layout.item_article,
            viewGroup,
            false
        )

        return ViewHolder(
            itemView,
            onBookmarkClick,
            onFullClick,
        )
    }

    override fun getItemCount() = articlesData.size

    fun setData(
        articles: List<ArticleModel>
    ) {
        articlesData = articles
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int
    ) {
        val articleModel = articlesData[position]
        holder.bind(articleModel)
    }
}