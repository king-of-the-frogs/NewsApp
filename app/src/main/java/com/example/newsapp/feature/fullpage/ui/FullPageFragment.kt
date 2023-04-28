package com.example.newsapp.feature.fullpage.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.FullPageActivity
import com.example.newsapp.R
import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.mainscreen.UiEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class FullPageFragment : Fragment(R.layout.fragment_full) {

    private val viewModel: FullPageScreenViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    private val adapter: FullPageAdapter by lazy {
        FullPageAdapter(
            onBookmarkClick = { article ->
                viewModel.processUiEvent(UiEvent.OnBookmarkClicked(article))
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_full, container, false)

        recyclerView = view.findViewById(R.id.rvFullPageArticles)
        recyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        adapter.setData(viewState.fullList)
    }

}