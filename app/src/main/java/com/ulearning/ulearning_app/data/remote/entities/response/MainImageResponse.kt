package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class MainImageResponse(
    @SerializedName("collection_name")
    var collectionName: String? = "",
    @SerializedName("conversions_disk")
    var conversionsDisk: String? = "",
    @SerializedName("created_at")
    var createdAt: String? = "",
    @SerializedName("custom_properties")
    var customProperties: List<Any>? = listOf(),
    @SerializedName("disk")
    var disk: String? = "",
    @SerializedName("file_name")
    var fileName: String? = "",
    @SerializedName("generated_conversions")
    var generatedConversions: List<Any>? = listOf(),
    @SerializedName("id")
    var id: Int? = 0,
    @SerializedName("manipulations")
    var manipulations: List<Any>? = listOf(),
    @SerializedName("mime_type")
    var mimeType: String? = "",
    @SerializedName("model_id")
    var modelId: Int? = 0,
    @SerializedName("model_type")
    var modelType: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("order_column")
    var orderColumn: Int? = 0,
    @SerializedName("original_url")
    var originalUrl: String? = "",
    @SerializedName("preview_url")
    var previewUrl: String? = "",
    @SerializedName("responsive_images")
    var responsiveImages: List<Any>? = listOf(),
    @SerializedName("size")
    var size: Int? = 0,
    @SerializedName("updated_at")
    var updatedAt: String? = "",
    @SerializedName("uuid")
    var uuid: String? = ""
)
