package com.ulearning.ulearning_app.presentation.model.entity

import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.isAlphaNumeric
import com.ulearning.ulearning_app.core.extensions.isMail
import com.ulearning.ulearning_app.core.functional.Failure
import java.io.Serializable


open class User constructor(
    var id: Int = -1,
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var lastName: String = "",
    var phone: String = "",
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
