package com.luteh.ecommerceexample

import android.app.Application
import com.facebook.FacebookSdk
import com.luteh.ecommerceexample.utils.Prefs
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.initPrefs(this)
        initTimber()
        FacebookSdk.sdkInitialize(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}