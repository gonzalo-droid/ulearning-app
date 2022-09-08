package com.ulearning.ulearning_app.presentation.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.startNewActivityClearStack
import com.ulearning.ulearning_app.databinding.ActivitySplashBinding
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.auth.LoginActivity
import com.ulearning.ulearning_app.presentation.features.home.view.HomeActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivityWithViewModel<ActivitySplashBinding, SplashViewModel>(),
    SplashViewState {

    override val binding: ActivitySplashBinding by dataBinding(ActivitySplashBinding::inflate)
    override val viewModel: SplashViewModel by viewModels()
    override val dataBindingViewModel = BR.splashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)

        screenSplash.setKeepOnScreenCondition { true }

        SplashReducer.instance(splashViewState = this)

        observeUiStates()


    }

    private fun observeUiStates() {

        viewModel.setEvent(SplashEvent.GotoActivity)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@SplashActivity, method = {
                state.collect { state ->
                    SplashReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@SplashActivity, method = {
                effect.collect { effect ->
                    SplashReducer.selectEffect(effect)
                }
            })
        }
    }


    override fun goHome() {
        startNewActivityClearStack<HomeActivity>()
        finish()
    }

    override fun goLogin() {
        startNewActivityClearStack<LoginActivity>()
        finish()
    }
}