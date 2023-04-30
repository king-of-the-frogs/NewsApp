package com.example.newsapp.feature.bookmarks.ui

import android.content.Intent
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.FullPageActivity
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.mainscreen.UiEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    private val adapter: BookmarksAdapter by lazy {
        BookmarksAdapter(
            onItemClick = {

            },
            onBookmarkDeleteClick = { article ->
                deleteFromBookmarks(article)
            },
            onFullClick = { article ->
                viewModel.processUiEvent(UiEvent.OnFullClick(article)).also {
                    requireContext().startActivity(
                        Intent(
                            requireContext(),
                            FullPageActivity::class.java
                        )
                    )
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bookmarks, container, false)
        recyclerView = view.findViewById(R.id.rvBookmarksArticles)
        recyclerView.adapter = adapter

        if (recyclerView.context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            recyclerView.setBackgroundResource(R.drawable.gradient2_bg)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.bookmarksList)
    }

    private fun deleteFromBookmarks(article: ArticleModel) {
        viewModel.deleteBookmark(article)
        adapter.setData(viewModel.viewState.value?.bookmarksList ?: emptyList())
        viewModel.deleteBookmark(article)
    }

}