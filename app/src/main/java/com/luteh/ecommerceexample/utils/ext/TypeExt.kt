package com.luteh.ecommerceexample.utils.ext

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */

fun Boolean.toInt(): Int = if (this) 1 else 0
fun Int.toBoolean(): Boolean = this == 1