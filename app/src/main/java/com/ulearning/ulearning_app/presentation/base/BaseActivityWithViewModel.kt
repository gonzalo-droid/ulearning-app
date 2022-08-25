package com.ulearning.ulearning_app.presentation.base

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.presentation.base.BaseActivity
import com.ulearning.ulearning_app.presentation.dialogs.LoadingDialog
import com.ulearning.ulearning_app.presentation.model.MessageFailure

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

    open fun getUseCaseFailureFromBase(failure: Failure) : MessageFailure {

        return when (failure) {
            is Failure.UnauthorizedOrForbidden ->  MessageFailure(
                userMessage = "Log out",
                message = failure.message)
            is Failure.None -> MessageFailure(
                userMessage = "Log out",
                message = "None")
            is Failure.NetworkConnectionLostSuddenly -> MessageFailure(
                userMessage = "Log out",
                message = "Connection lost suddenly. Check the wifi or mobile data.")
            is Failure.NoNetworkDetected -> MessageFailure(
                userMessage = "Sin conexión a internet",
                message ="No network detected")
            is Failure.SSLError -> MessageFailure(
                userMessage = "Error de red",
                message ="WARNING: SSL Exception")
            is Failure.TimeOut -> MessageFailure(
                userMessage = "Error de red",
                message ="Time out.")
            is Failure.ServerBodyError -> MessageFailure(
                userMessage = "Error del servidor",
                message = failure.message)
            is Failure.DataToDomainMapperFailure -> MessageFailure(
                userMessage = "Error al procesar la información",
                message = "Data to domain mapper failure: ${failure.mapperException}")
            is Failure.ServiceUncaughtFailure -> MessageFailure(
                userMessage = "Log out",
                message =failure.uncaughtFailureMessage)
            is Failure.DefaultError -> MessageFailure(
                userMessage = failure.message?:"Error",
                message = failure.message?:"Error")
        }
    }

}
