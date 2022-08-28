package com.ulearning.ulearning_app.domain.model

data class MainImage(
    var collection_name: String = "",
    var conversions_disk: String = "",
    var created_at: String = "",
    var custom_properties: List<Any> = listOf(),
    var disk: String = "",
    var file_name: String = "",
    var generated_conversions: List<Any> = listOf(),
    var id: Int = 0,
    var manipulations: List<Any> = listOf(),
    var mime_type: String = "",
    var model_id: Int = 0,
    var model_type: String = "",
    var name: String = "",
    var order_column: Int = 0,
    var originalUrl: String = "",
    var previewUrl: String = "",
    var responsive_images: List<Any> = listOf(),
    var size: Int = 0,
    var updated_at: String = "",
    var uuid: String = ""
)