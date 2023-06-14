package com.ulearning.ulearning_app.domain.model

data class FileItem(
    val bucket: String? = "",
    val code: String? = "",
    val courseId: Int? = 0,
    val fileInfo: FileInfo? = FileInfo(),
    val hash: String? = "",
    val id: Int? = 0,
    val name: String? = "",
    val number: String? = "",
    val type: String? = "",
    val userId: Int? = 0,
    val year: Int? = 0,
)
