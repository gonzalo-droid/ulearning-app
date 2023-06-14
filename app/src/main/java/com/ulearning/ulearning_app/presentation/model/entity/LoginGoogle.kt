package com.ulearning.ulearning_app.presentation.model.entity

import java.io.Serializable

open class LoginGoogle constructor(
    var email: String = "",
    var name: String = "",
    var familyName: String = "",
    var givenName: String = "",
) : Serializable
