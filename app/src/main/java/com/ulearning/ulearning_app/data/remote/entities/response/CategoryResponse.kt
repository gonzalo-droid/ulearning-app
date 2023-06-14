package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("card_image")
    var cardImage: CardImageResponse = CardImageResponse(),
    @SerializedName("color")
    var color: String = "",
    @SerializedName("description")
    var description: String = "",
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("parent")
    var parent: Any = Any(),
    @SerializedName("parent_id")
    var parentId: Any = Any(),
    @SerializedName("type")
    var type: String = ""
)
