package com.ulearning.ulearning_app.data.remote.entities.response

import com.google.gson.annotations.SerializedName

data class FileItemResponse(
    @SerializedName("bucket")
    val bucket: String? = "",
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("course_id")
    val courseId: Int? = 0,
    @SerializedName("data")
    val fileInfoResponse: FileInfoResponse? = FileInfoResponse(),
    @SerializedName("hash")
    val hash: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("number")
    val number: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("user_id")
    val userId: Int? = 0,
    @SerializedName("year")
    val year: Int? = 0,

/*    @SerializedName("deleted_at")
    val deletedAt: Any? = Any(),
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",*/
)
