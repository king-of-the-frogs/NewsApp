package com.example.newsapp.feature.mainscreen


import android.content.Intent
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.FullPageActivity
import com.example.newsapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainScreenViewModel by viewModel()
    private val recyclerView: RecyclerView by lazy { requireActivity().findViewById(R.id.rvArticles) }
    private val ivSearch: ImageView by lazy { requireActivity().findViewById(R.id.ivSearch) }
    private val tvTop: TextView by lazy { requireActivity().findViewById(R.id.tvTop) }
    private val etSearch: EditText by lazy { requireActivity().findViewById(R.id.etSearch) }
    private val layoutToolbar: ConstraintLayout by lazy { requireActivity().findViewById(R.id.layoutToolbar) }

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

        if (tvTop.context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            recyclerView.setBackgroundResource(R.drawable.gradient2_bg)
            ivSearch.setImageResource(R.drawable.ic_baseline_search_24_light)
            tvTop.setTextAppearance(R.style.Subtitle3)
            layoutToolbar.background.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black_100), PorterDuff.Mode.SRC_IN)
            etSearch.setTextAppearance(R.style.Subtitle2)
        }
    }

    private fun render(viewState: ViewState) {
        tvTop.isVisible = !viewState.isSearchEnabled
        etSearch.isVisible = viewState.isSearchEnabled
        adapter.setData(viewState.articleShown)
    }
}