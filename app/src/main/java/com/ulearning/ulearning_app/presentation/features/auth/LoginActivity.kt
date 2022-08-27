package com.ulearning.ulearning_app.presentation.features.auth

import android.os.Bundle
import androidx.activity.viewModels
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.startNewActivity
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.databinding.ActivityLoginBinding
import com.ulearning.ulearning_app.presentation.HomeActivity
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : BaseActivityWithViewModel<ActivityLoginBinding, LoginViewModel>(),
    LoginViewState {

    override val binding: ActivityLoginBinding by dataBinding(ActivityLoginBinding::inflate)

    override val viewModel: LoginViewModel by viewModels()

    override val dataBindingViewModel = BR.loginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginReducer.instance(viewState = this)
        observeUiStates()
    }

    private fun observeUiStates() {

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
    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun homeActivity() {
        closeLoadingDialog()
        startNewActivity<HomeActivity>()
    }
}