package com.luteh.ecommerceexample.presentation.activity.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.presentation.activity.login.LoginActivity
import com.luteh.ecommerceexample.presentation.activity.main.MainActivity
import com.luteh.ecommerceexample.utils.Constants
import com.luteh.ecommerceexample.utils.Prefs
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        runBlocking {
            delay(1000)
            if (Prefs.getBoolean(Constants.PREFS_IS_LOGGED, false)) {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}