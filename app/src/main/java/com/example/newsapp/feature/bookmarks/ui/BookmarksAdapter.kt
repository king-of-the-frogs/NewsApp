package com.example.newsapp.feature.bookmarks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.domain.ArticleModel

class BookmarksAdapter(
    private val onItemClick: (ArticleModel) -> Unit,
    private val onBookmarkAddClick: (ArticleModel) -> Unit
) :
    RecyclerView.Adapter<BookmarksAdapter.BookmarkViewHolder>() {

    private val bookmarksList = mutableListOf<ArticleModel>()

    class BookmarkViewHolder(itemView: View, private val onItemClick: (ArticleModel) -> Unit, private val onBookmarkAddClick: (ArticleModel) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvPublishedAt: TextView = itemView.findViewById(R.id.tvDate)
        private val btnAddBookmark: Button = itemView.findViewById(R.id.ivAddFav)

        fun bind(bookmarkData: ArticleModel) {
            tvTitle.text = bookmarkData.title
            tvAuthor.text = bookmarkData.author
            tvPublishedAt.text = bookmarkData.publishedAt
            btnAddBookmark.setOnClickListener {
                onBookmarkAddClick.invoke(bookmarkData)
            }
        }

        fun setOnClickListener(articleModel: ArticleModel) {
            itemView.setOnClickListener {
                onItemClick.invoke(articleModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_bookmarks, parent, false)
        return BookmarkViewHolder(itemView, onItemClick, onBookmarkAddClick)
    }


    override fun getItemCount(): Int {
        return bookmarksList.size
    }

    fun setData(bookmarks: List<ArticleModel>) {
        bookmarksList.clear()
        bookmarksList.addAll(bookmarks)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val articleModel = bookmarksList[position]
        holder.bind(articleModel)
        holder.setOnClickListener(articleModel)
    }
}