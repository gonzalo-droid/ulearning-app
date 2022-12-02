package com.ulearning.ulearning_app.presentation.model.entity

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.isAlphaNumeric
import com.ulearning.ulearning_app.core.extensions.isMail
import com.ulearning.ulearning_app.core.functional.Failure
import java.io.Serializable


open class User constructor(
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var lastName: String = "",
    var phone: String = "",
    var fcmToken: String = "",
) : Serializable {

    fun verifyLogin(): Pair<Boolean, Failure?> {

        return when {

            isEmailEmpty() -> {

                Pair(
                    false,
                    Failure.DefaultError(R.string.email_fail)
                )
            }
            !isEmail() -> {

                Pair(
                    false,
                    Failure.DefaultError(R.string.email_fail)
                )
            }
            isPasswordEmpty() -> {

                Pair(
                    false,
                    Failure.DefaultError(R.string.password_fail)
                )
            }
            else -> {

                Pair(
                    true,
                    null
                )
            }
        }
    }

    fun serviceTokenFirebase(
        response: (firebaseToken: String) -> Unit,
        error: (error: Failure) -> Unit
    ) {

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("FirebaseToken",task.exception?.message.toString())
                error(
                    Failure.DefaultError(R.string.firebase_token_message)
                )
                return@OnCompleteListener
            } else {

                response(task.result)
            }
        })
    }

    private fun isEmailEmpty(): Boolean {

        return email.isEmpty()
    }

    private fun isPasswordEmpty(): Boolean {

        return password.isEmpty()
    }

    private fun isEmail(): Boolean {

        return email.isMail()
    }

    private fun isAlphaNumeric(): Boolean {

        return password.isAlphaNumeric()
    }

    protected fun isPhoneEmpty(): Boolean {

        return phone.isEmpty()
    }
}
