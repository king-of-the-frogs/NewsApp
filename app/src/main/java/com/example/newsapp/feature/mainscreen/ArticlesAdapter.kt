package com.example.newsapp.feature.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel

class ArticlesAdapter(val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {


    private var articlesData: List<ArticleModel> = emptyList()
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
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
        viewHolder.itemView.setOnClickListener{
            onItemClicked(position)
        }

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.tvDate.text = articlesData[position].publishedAt
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articlesData.size

    fun setData (articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}
