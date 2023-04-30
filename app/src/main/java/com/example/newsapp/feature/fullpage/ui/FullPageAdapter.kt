package com.example.newsapp.feature.fullpage.ui

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
import com.squareup.picasso.Picasso
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FullPageAdapter(
    private val onBookmarkClick: (ArticleModel) -> Unit,
) :
    RecyclerView.Adapter<FullPageAdapter.ViewHolder>() {

    private var fullPageList = mutableListOf<ArticleModel>()

    class ViewHolder(
        itemView: View,
        private val onBookmarkClick: (ArticleModel) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvFullAuthor)
        private val tvDate: TextView = itemView.findViewById(R.id.tvFullDate)
        private val tvUrl: TextView = itemView.findViewById(R.id.tvFullUrl)
        private val tvUrlToImage: ImageView = itemView.findViewById(R.id.tvFullUrlToImage)
        private val tvContent: TextView = itemView.findViewById(R.id.tvFullContent)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvFullDescription)
        private val background: ImageView = itemView.findViewById(R.id.ivFullBackground)
//        private val ivAddFav: ImageView = itemView.findViewById(R.id.ivFullAddFav)

        fun bind(fullPageData: ArticleModel) {

            val formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'  'HH:mm"
            )
            val parsedDate = LocalDateTime.parse(
                fullPageData.publishedAt,
                DateTimeFormatter.ISO_DATE_TIME
            )
            val formattedDate = parsedDate.format(formatter)
            val url = fullPageData.urlToImage

            tvDate.text = formattedDate
            if (url != null && !url.isEmpty()) {
                Picasso.get().load(url).into(tvUrlToImage)
            } else {
                tvUrlToImage.setImageResource(R.drawable.ic_baseline_menu_book_24)
            }
            tvAuthor.text = fullPageData.author
            tvDescription.text = fullPageData.description
            tvContent.text = fullPageData.content

            tvUrl.setOnClickListener {
                val linkUrl = fullPageData.url
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
                val context = itemView.context
                context.startActivity(intent)
            }


//            ivAddFav.setOnClickListener {
//                onBookmarkClick.invoke(
//                    fullPageData
//                )
//                ivAddFav.setImageResource(R.drawable.ic_baseline_star_24)
//                if (tvTitle.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
//                    ivAddFav.setColorFilter(
//                        ContextCompat.getColor(
//                            itemView.context, R.color.black_100
//                        )
//                    )
//
//
//                }
//          }

            if (background.context.resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
            ) {
                background.setImageResource(R.drawable.form_full)
            }
        }

    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup, viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(
            viewGroup.context
        ).inflate(
            R.layout.item_full_article,
            viewGroup,
            false
        )

        return ViewHolder(
            itemView,
            onBookmarkClick,
        )
    }

    override fun getItemCount() = fullPageList.size

    fun setData(
        full: List<ArticleModel>
    ) {
        fullPageList.clear()
        fullPageList.addAll(full)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(
        holder: ViewHolder, position: Int
    ) {
        val articleModel = fullPageList[position]
        holder.bind(articleModel)
    }
}