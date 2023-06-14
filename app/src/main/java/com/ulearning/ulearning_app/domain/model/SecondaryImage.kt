package com.ulearning.ulearning_app.domain.model

data class SecondaryImage(
    var collectionName: String? = "",
    var conversionsDisk: String? = "",
    var customProperties: List<Any>? = arrayListOf(),
    var fileName: String? = "",
    var generatedConversions: List<Any>? = arrayListOf(),
    var id: Int? = 0,
    var manipulations: List<Any>? = arrayListOf(),
    var mimeType: String? = "",
    var modelId: Int? = 0,
    var modelType: String? = "",
    var name: String? = "",
    var orderColumn: Int? = 0,
    var originalUrl: String? = "",
    var previewUrl: String? = "",
    var responsiveImages: List<Any>? = arrayListOf(),
    var size: Int? = 0,
    var uuid: String? = ""
)
