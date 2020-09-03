package com.luteh.ecommerceexample.presentation.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.domain.Resource
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.presentation.activity.detailproduct.DetailProductActivity
import com.luteh.ecommerceexample.presentation.activity.favorite.FavoriteActivity
import com.luteh.ecommerceexample.presentation.activity.search.SearchActivity
import com.luteh.ecommerceexample.presentation.fragment.home.adapter.category.HomeCategoryAdapter
import com.luteh.ecommerceexample.presentation.fragment.home.adapter.product.HomeProductAdapter
import com.luteh.ecommerceexample.presentation.fragment.home.adapter.product.ProductItemCallback
import com.luteh.ecommerceexample.utils.ext.gone
import com.luteh.ecommerceexample.utils.ext.observe
import com.luteh.ecommerceexample.utils.ext.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_loading_error.*

@AndroidEntryPoint
class HomeFragment : Fragment(), ProductItemCallback {

    private val vm: HomeViewModel by viewModels()

    private val categoryAdapter = HomeCategoryAdapter()
    private val productAdapter = HomeProductAdapter(this)

    private val productList = mutableListOf<ProductPromo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoryRv()
        setupProductRv()
        bindViewModel()
        setupEvents()

        vm.getHomeData()
    }

    private fun bindViewModel() {
        observe(vm.homeData) {
            when (it) {
                is Resource.Loading -> {
                    layout_home_container.gone()
                    layout_loading_error_container.visible()

                    pb_loading_error.visible()
                    layout_error.gone()
                }
                is Resource.Success -> {
                    layout_loading_error_container.gone()
                    layout_home_container.visible()
                    it.data?.get(0)?.data?.let { data ->
                        productList.run {
                            clear()
                            addAll(data.productPromo)
                        }

                        categoryAdapter.setDataSource(data.category)
                        productAdapter.setDataSource(data.productPromo)
                    }
                }
                is Resource.Error -> {
                    layout_home_container.gone()
                    pb_loading_error.gone()
                    layout_error.visible()
                }
            }
        }
    }

    private fun setupCategoryRv() {
        rv_home_category.apply {
            setHasFixedSize(true)
            adapter = categoryAdapter
        }
    }

    private fun setupProductRv() {
        rv_home_product.apply {
            setHasFixedSize(true)
            adapter = productAdapter
        }
    }

    private fun setupEvents() {
        btn_error_retry.setOnClickListener {
            vm.getHomeData()
        }
    }

    override fun onClickProductItem(productPromo: ProductPromo) {
        val intent = Intent(requireContext(), DetailProductActivity::class.java).apply {
            putExtras(bundleOf(DetailProductActivity.KEY_BUNDLE_PRODUCT to productPromo))
        }
        startActivity(intent)
    }

    override fun onClickProductFavorite(productPromo: ProductPromo, loved: Int) {
        vm.setFavoriteProduct(productPromo, loved)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                startActivity(
                    Intent(requireContext(), SearchActivity::class.java).apply {
                        putExtras(bundleOf(SearchActivity.KEY_BUNDLE_PRODUCT_LIST to productList))
                    }
                )
            }
            R.id.menu_favorite -> {
                startActivity(Intent(requireContext(), FavoriteActivity::class.java))
            }
            else -> return false
        }
        return true
    }
}