package com.luteh.ecommerceexample.presentation.activity.detailproduct

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.utils.ext.observe
import com.luteh.ecommerceexample.utils.ext.toBoolean
import com.luteh.ecommerceexample.utils.ext.toInt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail_product.*
import java.util.*

@AndroidEntryPoint
class DetailProductActivity : AppCompatActivity() {

    companion object {
        const val KEY_BUNDLE_PRODUCT = "KEY_BUNDLE_PRODUCT"
    }

    private val vm: DetailProductViewModel by viewModels()

    private var productPromo: ProductPromo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        productPromo = intent.extras?.getParcelable(KEY_BUNDLE_PRODUCT)

        setupActionBar()
        bindViewModel()

        productPromo?.let {
            vm.getProductPromoById(it.id)
        }
    }

    private fun bindViewModel() {
        observe(vm.productLiveData) {
            Glide.with(this)
                .load(it.imageUrl)
                .into(iv_detail)

            tv_detail_name.text = it.title
            tv_detail_desc.text = it.description
            iv_detail_favorite.isSelected = it.loved.toBoolean()
            tv_detail_price.text = it.price

            iv_detail_favorite.setOnClickListener { v ->
                v.isSelected = !v.isSelected
                vm.setFavoriteProduct(it, v.isSelected.toInt())
            }

            btn_detail_buy.setOnClickListener { _ ->
                vm.insertPurchasedProduct(it, Calendar.getInstance().timeInMillis)
            }
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            title = getString(R.string.title_detail_product)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_product_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_share -> {
                shareContent()
            }
            else -> {
                return false
            }
        }
        return true
    }

    private fun shareContent() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.apply {
            type = "text/plain"
            productPromo?.let {
                putExtra(Intent.EXTRA_SUBJECT, it.title)
                putExtra(Intent.EXTRA_TEXT, it.description)
            }
        }

        startActivity(
            Intent.createChooser(
                intent,
                getString(R.string.title_share_using)
            )
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}