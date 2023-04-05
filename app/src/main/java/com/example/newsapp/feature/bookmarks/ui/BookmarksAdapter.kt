package com.example.newsapp.feature.bookmarks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.bookmarks.data.local.model.BookmarkEntity
import com.example.newsapp.feature.domain.ArticleModel

class BookmarksAdapter(private val onItemClick: (BookmarkEntity) -> Unit) :
    RecyclerView.Adapter<BookmarksAdapter.BookmarkViewHolder>() {

    private val bookmarksList = mutableListOf<BookmarkEntity>()

    class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
        private val tvPublishedAt: TextView = itemView.findViewById(R.id.tvDate)

        fun bind(bookmarkData: BookmarkEntity) {
            tvTitle.text = bookmarkData.title
            tvAuthor.text = bookmarkData.author
            tvPublishedAt.text = bookmarkData.publishedAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_bookmarks, parent, false)
        return BookmarkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmarkData = bookmarksList[position]
        holder.bind(bookmarkData)
        holder.itemView.setOnClickListener {
            onItemClick(bookmarkData)
        }
    }

    override fun getItemCount(): Int {
        return bookmarksList.size
    }

    fun setData(bookmarks: List<ArticleModel>) {
        bookmarksList.clear()
        notifyDataSetChanged()
    }
}