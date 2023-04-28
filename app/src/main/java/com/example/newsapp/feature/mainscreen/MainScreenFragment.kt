package com.example.newsapp.feature.mainscreen


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.FullPageActivity
import com.example.newsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val ivSearch: View by lazy { requireActivity().findViewById(R.id.ivSearch) }
    private val tvTop: TextView by lazy { requireActivity().findViewById(R.id.tvTop) }
    private val etSearch: EditText by lazy { requireActivity().findViewById(R.id.etSearch) }

    private val adapter: ArticlesAdapter by lazy {
        ArticlesAdapter(
            onBookmarkClick = { article ->
                viewModel.processUiEvent(UiEvent.OnBookmarkClicked(article))
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
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
        recyclerView.adapter = adapter

        ivSearch.setOnClickListener {
            viewModel.processUiEvent(UiEvent.OnSearchButtonClicked)
        }

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(text: Editable?) {
                viewModel.processUiEvent(UiEvent.OnSearchEdit(text.toString()))
            }
        })
    }

    private fun render(viewState: ViewState) {
        tvTop.isVisible = !viewState.isSearchEnabled
        etSearch.isVisible = viewState.isSearchEnabled
        adapter.setData(viewState.articleShown)
    }
}