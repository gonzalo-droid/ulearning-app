package com.ulearning.ulearning_app.domain.model

data class SecondaryImage(
    var collection_name: String?= "",
    var conversions_disk: String?= "",
    var created_at: String?= "",
    var custom_properties: List<Any>?= arrayListOf(),
    var disk: String?= "",
    var file_name: String?= "",
    var generated_conversions: List<Any>?= arrayListOf(),
    var id: Int?= 0,
    var manipulations: List<Any>?= arrayListOf(),
    var mime_type: String?= "",
    var model_id: Int?= 0,
    var model_type: String?= "",
    var name: String?= "",
    var order_column: Int?= 0,
    var original_url: String?= "",
    var preview_url: String?= "",
    var responsive_images: List<Any>?= arrayListOf(),
    var size: Int?= 0,
    var updated_at: String?= "",
    var uuid: String?= ""
)