package com.ulearning.ulearning_app.presentation.features.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.startNewActivity
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.databinding.ActivityLoginBinding
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.view.HomeActivity
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import com.ulearning.ulearning_app.presentation.model.entity.LoginFacebook
import com.ulearning.ulearning_app.presentation.model.entity.LoginGoogle
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException

@AndroidEntryPoint
class LoginActivity :
    BaseActivityWithViewModel<ActivityLoginBinding, LoginViewModel>(),
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

        loginInFacebook()

        observeUiStates()
    }

    private fun observeUiStates() {

        binding.logInGoogle.setOnClickListener {
            loginInGoogle()
        }

        /**
         * https://developers.facebook.com/docs/graph-api/reference/user
         * params avalible
         */

        binding.logInFacebook.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("public_profile", "email"))
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
        LoginManager.getInstance()
            .registerCallback(
                callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult) {
                        result.let {
                            val token = it.accessToken

                            val accessToken = AccessToken.getCurrentAccessToken()
                            val isLoggedIn = accessToken != null && !accessToken.isExpired
                            if (isLoggedIn) LoginManager.getInstance().logOut()

                            val request = GraphRequest.newMeRequest(
                                token
                            ) { `object`, response ->
                                try {
                                    /**
                                     * https://developers.facebook.com/docs/facebook-login/android
                                     * https://developers.facebook.com/docs/android/graph?locale=es_ES#userdata-troubleshooting
                                     *
                                     * get data with GraphRequest
                                     */

                                    val email = `object`!!.getString("email")
                                    val name = `object`.getString("name")
                                    val firstName = `object`.getString("first_name")
                                    val lastName = `object`.getString("last_name")
                                    val picture = `object`.getString("picture")
                                    viewModel.sendDataLoginInFacebook(
                                        LoginFacebook(
                                            email = email ?: "",
                                            name = name ?: "",
                                            firstName = firstName ?: "",
                                            lastName = lastName ?: "",
                                            picture = picture ?: "",
                                        )
                                    )
                                } catch (e: JSONException) {
                                    Log.e("SingInFacebook", "json: " + e.message.toString())
                                }
                            }
                            val parameters = Bundle()
                            parameters.putString("fields", "email,first_name,last_name,name,picture")
                            request.parameters = parameters
                            request.executeAsync()
                        }
                    }

                    override fun onCancel() {
                        Log.e("SingInFacebook", "onCancel")
                    }

                    override fun onError(error: FacebookException) {
                        Log.e("SingInFacebook", "Exception: " + error.message.toString())
                        // LoginManager.getInstance().logOut()
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
