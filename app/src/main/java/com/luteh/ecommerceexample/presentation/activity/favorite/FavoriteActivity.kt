package com.luteh.ecommerceexample.presentation.activity.favorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.presentation.adapter.CommonProductAdapter
import com.luteh.ecommerceexample.utils.ext.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_favorite.*

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val vm: FavoriteViewModel by viewModels()

    private val adapter = CommonProductAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setupActionBar()
        setupRecyclerView()
        bindViewModel()
    }

    private fun setupRecyclerView() {
        rv_favorite.apply {
            adapter = this@FavoriteActivity.adapter
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            title = getString(R.string.title_favorite_product)

            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun bindViewModel() {
        observe(vm.favorites) {
                adapter.setDataSource(it)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}