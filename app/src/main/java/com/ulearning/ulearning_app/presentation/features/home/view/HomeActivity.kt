package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.databinding.ActivityHomeBinding
import com.ulearning.ulearning_app.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.mobile_navigation)
        navView.setupWithNavController(navController)
    }

    override val binding: ActivityHomeBinding by dataBinding(ActivityHomeBinding::inflate)
}
