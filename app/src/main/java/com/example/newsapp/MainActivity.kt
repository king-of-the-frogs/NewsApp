package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.feature.bookmarks.ui.BookmarksFragment
import com.example.newsapp.feature.mainscreen.MainScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val bottomNavigationMenu: BottomNavigationView by lazy { findViewById(R.id.bnvBar) }
    private lateinit var appDatabase: AppDataBaseFullPage

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
                else -> {}
            }
            true
        }
        bottomNavigationMenu.selectedItemId = R.id.itemMain


    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalScope.launch {
            appDatabase.fullPageDao().nukeTable()
        }
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
            .commit()
    }
}

