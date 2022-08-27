package com.ulearning.ulearning_app.presentation.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.startNewActivityClearStack
import com.ulearning.ulearning_app.databinding.ActivitySplashBinding
import com.ulearning.ulearning_app.presentation.base.BaseActivity
import com.ulearning.ulearning_app.presentation.features.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)



        screenSplash.setKeepOnScreenCondition { true }

        startNewActivityClearStack<LoginActivity>()
        finish()

    }

    override val binding: ActivitySplashBinding by dataBinding(ActivitySplashBinding::inflate)
}