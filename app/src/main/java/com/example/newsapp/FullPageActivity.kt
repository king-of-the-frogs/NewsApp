package com.example.newsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.feature.bookmarks.ui.BookmarksFragment
import com.example.newsapp.feature.domain.ArticleModel
import com.example.newsapp.feature.fullpage.FullPageAdapter
import com.example.newsapp.feature.mainscreen.MainScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class FullPageActivity : AppCompatActivity() {

    private val bottomNavigationMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_page)

        bottomNavigationMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemMain -> {
                    selectTab(MainScreenFragment())
                }
                R.id.itemBookmarks -> {
                    selectTab(BookmarksFragment())
                }
                else -> {}
            }
            true
        }
        bottomNavigationMenu.selectedItemId = R.id.itemMain
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commit()
    }

    companion object {
        fun newIntent(context: Context, articleModel: ArticleModel): Intent {
            val intent = Intent(context, FullPageActivity::class.java)
            intent.putExtra("articleModel", articleModel)
            return intent
        }
    }
}
