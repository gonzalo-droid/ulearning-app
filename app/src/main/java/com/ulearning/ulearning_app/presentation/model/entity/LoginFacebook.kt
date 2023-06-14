package com.ulearning.ulearning_app.presentation.model.entity

import java.io.Serializable

open class LoginFacebook constructor(
    var email: String = "",
    var name: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var picture: String = "",
) : Serializable
