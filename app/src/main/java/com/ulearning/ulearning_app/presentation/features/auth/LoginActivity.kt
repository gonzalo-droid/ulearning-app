package com.ulearning.ulearning_app.presentation.features.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.startNewActivity
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.databinding.ActivityLoginBinding
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.view.HomeActivity
import com.ulearning.ulearning_app.presentation.features.splash.SplashEvent
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import com.ulearning.ulearning_app.presentation.model.entity.LoginGoogle
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : BaseActivityWithViewModel<ActivityLoginBinding, LoginViewModel>(),
    LoginViewState {

    override val binding: ActivityLoginBinding by dataBinding(ActivityLoginBinding::inflate)

    override val viewModel: LoginViewModel by viewModels()

    override val dataBindingViewModel = BR.loginViewModel

    val GOOGLE_SIGN_IN = 222
    val FACEBOOK_SIGN_IN = 111

    private val callbackManager = CallbackManager.Factory.create()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginReducer.instance(viewState = this)
        observeUiStates()
    }

    private fun observeUiStates() {

        binding.logInGoogle.setOnClickListener {
            loginInGoogle()
        }

        viewModel.apply {
            lifecycleScopeCreate(activity = this@LoginActivity, method = {
                state.collect { state ->
                    LoginReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@LoginActivity, method = {
                effect.collect { effect ->
                    LoginReducer.selectEffect(effect)
                }
            })
        }
    }

    override fun messageFailure(failure: Failure) {
        closeLoadingDialog()
        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))

    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun loginInFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult?) {
                    result?.let {
                        val token = it.accessToken
                        val n = FacebookAuthProvider.getCredential(token.token)

                    }
                }

                override fun onCancel() {
                    TODO("Not yet implemented")
                }

                override fun onError(error: FacebookException?) {
                    Log.e("SingInFaceebook", error?.message.toString())
                }

            }
        )
    }

    override fun loginInGoogle() {

        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, googleConf)

        googleClient.signOut()

        startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        callbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    viewModel.sendDataLoginInGoogle(
                        LoginGoogle(
                            email = account.email ?: "",
                            name = account.displayName ?: "",
                            familyName = account.familyName ?: "",
                            givenName = account.givenName ?: ""
                        )
                    )

                }
            } catch (e: ApiException) {
                Log.e("ApiExceptionGoogle", e.message.toString())
                messageFailure(Failure.DefaultError(R.string.error_sign_in_google))
            }

        }
    }


    override fun homeActivity() {
        closeLoadingDialog()
        startNewActivity<HomeActivity>()
        finish()
    }
}