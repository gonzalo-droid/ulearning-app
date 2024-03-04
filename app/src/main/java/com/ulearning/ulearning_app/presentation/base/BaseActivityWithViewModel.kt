package com.ulearning.ulearning_app.presentation.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.dialogs.LoadingDialog
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import com.ulearning.ulearning_app.presentation.utils.BindingUtil

abstract class BaseActivityWithViewModel<VBinding : ViewDataBinding, ViewModelType : ViewModel> :
    BaseActivity<VBinding>() {
    protected abstract val viewModel: ViewModelType

    protected abstract val dataBindingViewModel: Int

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding.setVariable(dataBindingViewModel, viewModel)
        binding.executePendingBindings()
    }

    open fun getUseCaseFailureFromBase(failure: Failure): MessageDesign {
        return BindingUtil.reducerFailure(failure)
    }
}
