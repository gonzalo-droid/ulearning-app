package com.ulearning.ulearning_app.core.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.remove() {
    this.visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun Context.drawable(@DrawableRes drawable: Int): Drawable? {
    return AppCompatResources.getDrawable(this, drawable)
}

infix fun View.click(click:()->Unit){
    setOnClickListener {click}
}