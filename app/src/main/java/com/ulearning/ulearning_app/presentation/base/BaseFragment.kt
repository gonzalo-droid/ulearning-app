package com.ulearning.ulearning_app.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ulearning.ulearning_app.core.extensions.hideKeyboard
import com.ulearning.ulearning_app.presentation.dialogs.LoadingDialog

abstract class BaseFragment<ViewDataBindingClass : ViewDataBinding> : Fragment() {
    private var loadingDialog: LoadingDialog? = null
    protected abstract val binding: ViewDataBindingClass

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureHideKeyboar(binding.root)
        return binding.root
    }

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    protected fun showLoadingDialog(loadingText: String = "") {
        loadingDialog?.dismiss()
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(loadingText)
            loadingDialog!!.show(parentFragmentManager, "loading_dialog")
        } else {
            loadingDialog!!.show(parentFragmentManager, "loading_dialog")
        }
    }

    protected fun closeLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog?.dismiss()
        }
    }

    private fun removeLoadingDialogFromBackStack() {
        loadingDialog?.dismiss()
    }

    //region SnackBar
    protected fun showSnackBar(
        rootView: View,
        contentText: Any,
        duration: Int = Snackbar.LENGTH_LONG
    ) {
        val text = when (contentText) {
            is String -> contentText
            is Int -> getString(contentText)
            else -> ""
        }
        Snackbar.make(rootView, text, duration).show()
    }

    @SuppressLint("ClickableViewAccessibility")
    open fun configureHideKeyboar(view: View) {

        if (view !is EditText) {
            view.setOnTouchListener { v, _ ->
                v.hideKeyboard()
                false
            }
        }

        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                configureHideKeyboar(innerView)
            }
        }
    }

    protected fun showSnackBar(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG
        ).show()
    }

    protected fun showToast(message: Any?) {
        val textToShow = when (message) {
            is Int -> getString(message)
            is String -> message
            else -> {
                ""
            }
        }
        Toast.makeText(requireContext(), textToShow, Toast.LENGTH_LONG).show()
    }

    protected fun showToasLong(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    protected fun logError(message: Any?) {
        Log.e(this.javaClass.simpleName, "LOG ERROR: $message")
    }

    protected fun logDebug(message: Any?) {
        Log.d(this.javaClass.simpleName, "LOG DEBUG: $message")
    }

}
