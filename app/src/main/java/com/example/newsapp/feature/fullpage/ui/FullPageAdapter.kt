package com.example.newsapp.feature.fullpage.ui

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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
        private val tvTitle: TextView = itemView.findViewById(R.id.tvFullTitle)
        private val tvAuthor: TextView = itemView.findViewById(R.id.tvFullAuthor)
        private val tvDate: TextView = itemView.findViewById(R.id.tvFullDate)
        private val tvUrl: TextView = itemView.findViewById(R.id.tvFullUrl)
        private val tvUrlToImage: TextView = itemView.findViewById(R.id.tvFullUrlToImage)
        private val tvContent: TextView = itemView.findViewById(R.id.tvFullContent)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvFullDescription)
//        private val ivAddFav: ImageView = itemView.findViewById(R.id.ivFullAddFav)

        fun bind(fullPageData: ArticleModel) {

            val formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd'  'HH:mm"
            )
            val parsedDate = LocalDateTime.parse(
                fullPageData.pubDate,
                DateTimeFormatter.ISO_DATE_TIME
            )
            val formattedDate = parsedDate.format(formatter)

            tvDate.text = formattedDate
            tvTitle.text = fullPageData.title
            tvAuthor.text = fullPageData.creator
            tvDescription.text = fullPageData.description
            tvContent.text = fullPageData.content
            tvUrlToImage.text = fullPageData.image_url

            tvUrl.setOnClickListener {
                val linkUrl = fullPageData.link
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


            if (tvTitle.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
                tvDate.setTextAppearance(R.style.Subtitle1)
                tvAuthor.setTextAppearance(R.style.Subtitle1)
                tvTitle.setTextAppearance(R.style.Subtitle1)
                tvUrl.setTextAppearance(R.style.Subtitle1)
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