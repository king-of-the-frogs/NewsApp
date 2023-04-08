package com.example.newsapp.feature.fullnews

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NewsAdapter(
    private val onItemClick: (ArticleModel) -> Unit,
    private val OnFullClick: (ArticleModel) -> Unit,

) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val bookmarksList = mutableListOf<ArticleModel>()

    class ViewHolder(
        itemView: View,
        private val onItemClick: (ArticleModel) -> Unit,
        private val OnFullClick: (ArticleModel) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvFullInfo)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvFullAuthor)
        private val tvDate: TextView = itemView.findViewById(R.id.tvFullDate)
        private val tvUrl: TextView = itemView.findViewById(R.id.tvFullUrl)
        private val tvDel: ImageView = itemView.findViewById(R.id.ivDelFav)

        fun bind(bookmarkData: ArticleModel, position: Int) {

            val formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'  'HH:mm:ss"
            )
            val parsedDate = LocalDateTime.parse(
                bookmarkData.publishedAt,
                DateTimeFormatter.ISO_DATE_TIME
            )
            val formattedDate = parsedDate.format(formatter)

            tvDate.text = formattedDate
            tvTitle.text = bookmarkData.title
            tvAuthor.text = bookmarkData.author

            tvUrl.setOnClickListener {
                val linkUrl = bookmarkData.url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
                val context = itemView.context
                context.startActivity(intent)
            }



            if (tvTitle.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                tvDate.setTextAppearance(R.style.Subtitle1)
                tvAuthor.setTextAppearance(R.style.Subtitle1)
                tvTitle.setTextAppearance(R.style.Subtitle1)
                tvUrl.setTextAppearance(R.style.Subtitle1)
                tvDel.setColorFilter(
                    ContextCompat.getColor(
                    itemView.context, R.color.black_100
                )
                )
            }
        }

        private fun setOnClickListener(articleModel: ArticleModel) {
            itemView.setOnClickListener {
                onItemClick.invoke(articleModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_full_article, parent, false)
        return ViewHolder(itemView, onItemClick, OnFullClick)
    }

    override fun getItemCount() = bookmarksList.size

    fun setData(bookmarks: List<ArticleModel>) {
        bookmarksList.clear()
        bookmarksList.addAll(bookmarks)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articleModel = bookmarksList[position]
        holder.bind(articleModel, position)
    }
}