package com.ulearning.ulearning_app.core.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.util.Base64.DEFAULT
import android.util.Base64.encodeToString
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import java.lang.Byte.decode
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import android.util.Base64
import com.ulearning.ulearning_app.core.utils.Config


@SuppressLint("SimpleDateFormat")
fun Date.dateFormat(format: String): String {

    return SimpleDateFormat(format).format(this)
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("SimpleDateFormat")
fun String.dateFormat(format: String): Date {

    val dateFormat = SimpleDateFormat(format)

    return dateFormat.parse(this)
}


fun String.encode(): String {
    return android.util.Base64.encodeToString(
        this.toByteArray(charset("UTF-8")),
        android.util.Base64.DEFAULT
    )
}

fun String.decode(): String {
    return String(android.util.Base64.decode(this, android.util.Base64.DEFAULT), charset("UTF-8"))
}


fun String.toBigDecimalReturnString(): String {
    val double = this.toDouble()
    val big = BigDecimal(double).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
    return big.toString()
}


infix fun Double.decimalFormat(format: String): String = DecimalFormat(format).format(this)

infix fun Int.decimalFormat(format: String): String = DecimalFormat(format).format(this)


fun String.isMail(): Boolean = this.matches(Regex(Config.EMAIL_REGEX))

fun String.isAlphaNumeric(): Boolean = this.matches(Regex(Config.ALPHA_NUMERIC_REGEX))

fun lifecycleScopeCreate(activity: Activity, method: suspend () -> Unit) =
    (activity as AppCompatActivity).lifecycleScope.launch {

        /**
         * Api implements repeatOnLifecycle
         *
         * source: https://medium.com/androiddevelopers/repeatonlifecycle-api-design-story-8670d1a7d333
         */
        activity.repeatOnLifecycle(Lifecycle.State.CREATED) {

            method()
        }

    }
