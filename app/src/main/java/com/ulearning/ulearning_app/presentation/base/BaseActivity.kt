package com.ulearning.ulearning_app.presentation.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.hideKeyboard
import com.ulearning.ulearning_app.presentation.dialogs.LoadingDialog
import kotlinx.coroutines.launch

abstract class BaseActivity<VBinding : ViewDataBinding> :
    AppCompatActivity() {

    protected abstract val binding: VBinding

    private var loadingDialog: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setContentView(binding.root)
        binding.lifecycleOwner = this
        configureHideKeyboar(binding.root)
        observeUserIntentStates()
    }

    private fun observeUserIntentStates() {
        lifecycleScope.launch { handleIntentStates() }
    }

    open suspend fun handleIntentStates() {}

    protected fun stringRes(resource: Int): String {
        return getString(resource)
    }

    protected fun showSnackBar(
        rootView: View,
        contentText: String?,
        duration: Int = Snackbar.LENGTH_LONG,
    ) {
        Snackbar.make(rootView, contentText ?: "error desconocido", duration).show()
    }

    protected fun showLoadingDialog(loadingText: String = "") {
        loadingDialog?.dismiss()
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog(loadingText)
            loadingDialog!!.show(supportFragmentManager, "loading_dialog")
        } else {
            loadingDialog!!.show(supportFragmentManager, "loading_dialog")
        }
    }

    protected fun closeLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog?.dismiss()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    open fun configureHideKeyboar(view: View) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { v, _ ->
                v.hideKeyboard()
                false
            }
        }

        // If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                configureHideKeyboar(innerView)
            }
        }
    }

    protected fun showToastShort(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun showToastLong(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun logError(errorMessage: Any?) {
        Log.e(this.javaClass.simpleName, "ERROR: $errorMessage")
    }

    protected fun logDebug(debugMessage: Any?) {
        Log.e(this.javaClass.simpleName, "DEBUG: $debugMessage")
    }

    protected fun logInfo(infoMessage: Any?) {
        Log.i(this.javaClass.simpleName, "INFO: $infoMessage")
    }

    open fun replaceFragment(fragment: Fragment) {
        pushFragment(fragment, R.id.mobile_navigation, false)
    }
    open fun pushFragment(
        fragment: Fragment,
        container: Int,
        addBackStack: Boolean,
        vararg animations: Int,
    ) {
        val transaction = supportFragmentManager.beginTransaction()
        val tag = fragment.javaClass.simpleName
        if (addBackStack) {
            transaction.addToBackStack(tag)
        }
        transaction.replace(container, fragment, tag)
        try {
            transaction.commit()
        } catch (e: Exception) {
            return
        }
    }
}
