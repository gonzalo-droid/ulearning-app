package com.ulearning.ulearning_app.presentation.features.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.databinding.ActivityHomeBinding
import com.ulearning.ulearning_app.databinding.FragmentHomeBinding
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeReducer
import com.ulearning.ulearning_app.presentation.features.home.HomeViewState
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CourseViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment :
    BaseFragmentWithViewModel<FragmentHomeBinding, HomeViewModel>(),
    HomeViewState {

    override val binding: FragmentHomeBinding by dataBinding(FragmentHomeBinding::inflate)

    override val viewModel: HomeViewModel by viewModels()

    override val dataBindingViewModel = BR.loginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HomeReducer.instance(viewState = this)
        observeUiStates()
    }

    private fun observeUiStates() {

    }

    override fun messageFailure(failure: Failure) {
        TODO("Not yet implemented")
    }

    override fun loading() {
        TODO("Not yet implemented")
    }

    override fun homeActivity() {
        TODO("Not yet implemented")
    }

}