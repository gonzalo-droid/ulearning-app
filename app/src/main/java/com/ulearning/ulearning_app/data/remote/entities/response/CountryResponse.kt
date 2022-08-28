package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName


data class CountryResponse(
    @SerializedName("code")
    var code: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("phone_code")
    var phoneCode: String = ""
)