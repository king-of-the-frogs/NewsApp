package com.example.newsapp.feature.mainscreen


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter { index ->
            viewModel.processUiEvent(UiEvent.OnArticleClicked(index))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter
    }

    private fun render (viewState: ViewState) {
        adapter.setData(viewState.articles)
    }
}