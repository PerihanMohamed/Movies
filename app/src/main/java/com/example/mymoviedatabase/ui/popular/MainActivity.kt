package com.example.mymoviedatabase.ui.popular

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mymoviedatabase.R
import com.example.mymoviedatabase.ui.DiscoverFragment
import com.example.mymoviedatabase.ui.FavoriteFragment
import com.example.mymoviedatabase.utils.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))


        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment_main,
                    PopularFragment()
                )
                .commit()
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_item_top_rated -> {
                Category.TOPRATED
                true
            }
            R.id.menu_item_popular_movies -> {
                Category.POPULAR
                true
            }
            R.id.menu_item_up_coming ->{
                Category.UPCOMING
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    }

