package com.luteh.ecommerceexample.presentation.activity.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.presentation.activity.main.MainActivity
import com.luteh.ecommerceexample.utils.Constants
import com.luteh.ecommerceexample.utils.Prefs
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber
import java.util.*


class LoginActivity : AppCompatActivity() {

    companion object {
        const val RC_SIGN_IN = 2001
    }

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initGoogleSignIn()
        initFacebookSignIn()
        setupEvents()
    }

    private fun initGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }


    private fun initFacebookSignIn() {
        callbackManager = CallbackManager.Factory.create()
    }

    private fun setupEvents() {
        btn_login.setOnClickListener {
            goToHomePage()
        }

        btn_login_google.setOnClickListener {
            val signInIntent: Intent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
        btn_login_facebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"));
        }

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    // App code
                    Timber.d("success")
                    goToHomePage()
                }

                override fun onCancel() {
                    // App code
                    Timber.d("cancel")
                }

                override fun onError(exception: FacebookException) {
                    // App code
                    Timber.d("error")
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleGoogleSignInResult(task)
        }
    }

    private fun handleGoogleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            onFinishGoogleSignIn(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Timber.e("signInResult:failed code=${e.statusCode}")
            onFinishGoogleSignIn(null)
        }
    }

    private fun onFinishGoogleSignIn(account: GoogleSignInAccount?) {
        account?.run {
            goToHomePage()
        }
    }

    private fun goToHomePage() {
        if (cb_login.isChecked) {
            Prefs.putBoolean(Constants.PREFS_IS_LOGGED, true)
        }
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}