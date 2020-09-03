package com.luteh.ecommerceexample.presentation.activity.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.presentation.adapter.CommonProductAdapter
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    companion object {
        const val KEY_BUNDLE_PRODUCT_LIST = "KEY_BUNDLE_PRODUCT_LIST"
    }

    private val adapter = CommonProductAdapter()
    private var productList: List<ProductPromo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        productList = intent.extras?.getParcelableArrayList(KEY_BUNDLE_PRODUCT_LIST)

        setupActionBar()
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupActionBar() {
        setSupportActionBar(toolbar_search)
        title = ""
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        rv_search.apply {
            adapter = this@SearchActivity.adapter
        }
        productList?.let {
            adapter.setDataSource(it)
        }
    }

    private fun setupSearchView() {
        sv_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText ?: "")
                return false
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}