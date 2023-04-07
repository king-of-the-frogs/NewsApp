package com.example.newsapp.feature.bookmarks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    private val viewModel: BookmarksScreenViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    private val adapter: BookmarksAdapter by lazy {
        BookmarksAdapter(
            onItemClick = { index ->
                // Обработка клика на элемент списка
            },
            onBookmarkAddClick = { article ->
                addToBookmarks(article)
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
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.bookmarksList)
    }

    private fun addToBookmarks(article: ArticleModel) {
        viewModel.addBookmark(article)
    }
}