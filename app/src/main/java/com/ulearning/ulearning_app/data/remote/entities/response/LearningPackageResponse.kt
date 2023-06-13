package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName

data class LearningPackageResponse(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("description_large")
    val descriptionLarge: String?,
    @SerializedName("description_short")
    val descriptionShort: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_shop")
    val isShop: Boolean?,
    @SerializedName("main_image")
    val mainImage: MainImageResponse?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: String?
)