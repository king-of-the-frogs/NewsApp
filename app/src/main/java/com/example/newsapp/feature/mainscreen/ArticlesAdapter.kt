package com.example.newsapp.feature.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {


    private var articlesData: List<ArticleModel> = emptyList()
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tvTitle)
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

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = articlesData[position].title
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = articlesData.size

    fun setData (articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}
