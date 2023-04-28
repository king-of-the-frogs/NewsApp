package com.example.newsapp.feature.bookmarks.ui

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.domain.ArticleModel
import kotlinx.coroutines.NonDisposableHandle.parent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BookmarksAdapter(
    private val onItemClick: (ArticleModel) -> Unit,
    private val onBookmarkDeleteClick: (ArticleModel) -> Unit,
    private val onFullClick: (ArticleModel) -> Unit,
) :
    RecyclerView.Adapter<BookmarksAdapter.ViewHolder>() {

    private val bookmarksList = mutableListOf<ArticleModel>()
    private val bookmarksShown = mutableListOf<ArticleModel>()

    class ViewHolder(
        itemView: View,
        private val onItemClick: (ArticleModel) -> Unit,
        private val onBookmarkDeleteClick: (ArticleModel) -> Unit,
        private val onFullClick: (ArticleModel) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvBookmarksInfo)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvBookmarksAuthor)
        private val tvDate: TextView = itemView.findViewById(R.id.tvBookmarksDate)
        private val tvUrl: TextView = itemView.findViewById(R.id.tvBookmarksUrl)
        private val tvDel: ImageView = itemView.findViewById(R.id.ivDelFav)
        private val tvFull: ImageView = itemView.findViewById(R.id.ivBookmarksFull)

        fun bind(bookmarkData: ArticleModel, position: Int) {

            val formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'  'HH:mm"
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

            tvDel.setOnClickListener {
                onBookmarkDeleteClick.invoke(bookmarkData)
            }
            setOnClickListener(bookmarkData)

            tvFull.setOnClickListener{
                onFullClick.invoke(
                    bookmarkData
                )
            }

            if (tvTitle.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                tvDate.setTextAppearance(R.style.Subtitle1)
                tvAuthor.setTextAppearance(R.style.Subtitle1)
                tvUrl.setTextAppearance(R.style.Subtitle1)
                tvDel.setColorFilter(ContextCompat.getColor(
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

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(
            viewGroup.context
        ).inflate(
            R.layout.item_bookmark_article,
            viewGroup,
            false
        )

        return ViewHolder(
            itemView,
            onItemClick,
            onBookmarkDeleteClick,
            onFullClick
        )
    }

    override fun getItemCount() = bookmarksList.size

    fun setData(
        bookmarks: List<ArticleModel>
    ) {
        bookmarksList.clear()
        bookmarksList.addAll(bookmarks)
        bookmarksShown.clear()
        bookmarksShown.addAll(bookmarks)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int
    ) {
        val articleModel = bookmarksList[position]
        holder.bind(articleModel, position)
    }
}