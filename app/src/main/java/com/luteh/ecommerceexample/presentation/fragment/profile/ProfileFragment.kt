package com.luteh.ecommerceexample.presentation.fragment.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.presentation.adapter.CommonProductAdapter
import com.luteh.ecommerceexample.utils.ext.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_profile.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val vm: ProfileViewModel by viewModels()

    private val purchasedAdapter = CommonProductAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRv()
        observe(vm.purchasedProducts) {
            purchasedAdapter.setDataSource(it)
        }
    }

    private fun setupRv() {
        rv_profile.apply {
            adapter = purchasedAdapter
        }
    }
}