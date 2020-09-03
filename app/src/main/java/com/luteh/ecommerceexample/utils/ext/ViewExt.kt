package com.luteh.ecommerceexample.utils.ext

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * Created by Luthfan Maftuh on 3/29/2020.
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun SwipeRefreshLayout.startRefreshing() {
    isRefreshing = true
}

fun SwipeRefreshLayout.stopRefreshing() {
    isRefreshing = false
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun Context.color(colorResId: Int): Int =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getColor(colorResId)
    } else {
        resources.getColor(colorResId)
    }

fun ImageView.setTint(@ColorRes colorResId: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorResId)))
}

fun TextView.setColor(@ColorRes colorResId: Int) {
    this.setTextColor(ContextCompat.getColor(context, colorResId))
}
