package com.example.newsapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.newsapp.feature.fullpage.ui.FullPageFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@Suppress
class FullPageActivity : AppCompatActivity() {

    private lateinit var appDatabase: AppDataBaseFullPage
    private val containerFull: FrameLayout by lazy { findViewById(R.id.containerFull) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_page)

        appDatabase = AppDataBaseFullPage.getInstance(applicationContext)

        selectTab(FullPageFragment())

        if (containerFull.context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
        ) {
            containerFull.setBackgroundResource(R.drawable.gradient2_bg)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        GlobalScope.launch {
            appDatabase.fullPageDao().wipeData()
        }
    }

    private fun selectTab(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.containerFull, fragment)
            .commit()
    }
}
