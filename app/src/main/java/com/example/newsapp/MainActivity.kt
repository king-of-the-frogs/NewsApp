package com.example.newsapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.feature.bookmarks.ui.BookmarksFragment
import com.example.newsapp.feature.mainscreen.MainScreenFragment
import com.example.newsapp.feature.worldnews.WorldNewsScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private val bottomNavigationMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }
    private val container: FrameLayout by lazy { findViewById(R.id.container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemMain -> {
                    selectTab(MainScreenFragment())
                }
                R.id.itemBookmarks -> {
                    selectTab(BookmarksFragment())
                }
                R.id.itemWorld -> {
                    selectTab(WorldNewsScreenFragment())
                }
                else -> {}
            }
            true
        }
        bottomNavigationMenu.selectedItemId = R.id.itemMain

        if (container.context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            container.setBackgroundResource(R.drawable.gradient2_bg)
        }
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commit()
    }
}

