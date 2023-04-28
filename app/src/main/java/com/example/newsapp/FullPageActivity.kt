package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.feature.bookmarks.ui.BookmarksFragment
import com.example.newsapp.feature.fullpage.ui.FullPageFragment
import com.example.newsapp.feature.mainscreen.MainScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Suppress
class FullPageActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDataBaseFullPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_page)

        appDatabase = AppDataBaseFullPage.getInstance(applicationContext)

        selectTab(FullPageFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalScope.launch {
            appDatabase.fullPageDao().nukeTable()
        }
    }


    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.containerFull, fragment)
            .commit()
    }
}
