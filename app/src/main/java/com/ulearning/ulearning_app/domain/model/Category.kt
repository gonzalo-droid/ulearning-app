package com.ulearning.ulearning_app.domain.model

data class Category(
    var card_image: CardImage = CardImage(),
    var color: String = "",
    var description: Any = Any(),
    var id: Int = 0,
    var name: String = "",
    var parent: Any = Any(),
    var parent_id: Any = Any(),
    var type: String = ""
)