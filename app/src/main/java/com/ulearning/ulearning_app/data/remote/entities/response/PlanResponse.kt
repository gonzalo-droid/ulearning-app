package com.ulearning.ulearning_app.data.remote.entities.response


import com.google.gson.annotations.SerializedName

data class PlanResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("next_renovation_at")
    var nextRenovationAt: Any = Any(),
    @SerializedName("type")
    var type: String = "",
    @SerializedName("user_id")
    var userId: Int = 0
)