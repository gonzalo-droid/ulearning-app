package com.ulearning.ulearning_app.domain.model

data class Plan(
    var id: Int?= 0,
    var next_renovation_at: Any?= null,
    var type: String?= "",
    var user_id: Int?= 0
)