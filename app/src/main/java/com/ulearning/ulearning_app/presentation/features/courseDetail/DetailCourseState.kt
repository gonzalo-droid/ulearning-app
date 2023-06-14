package com.ulearning.ulearning_app.presentation.features.courseDetail

import com.ulearning.ulearning_app.domain.model.*
import com.ulearning.ulearning_app.presentation.base.UiState

sealed class DetailCourseState : UiState {

    object Idle : DetailCourseState()

    object Loading : DetailCourseState()

    data class DataDetailCourse constructor(val data: Subscription) : DetailCourseState()

    data class ListTopic constructor(val topics: List<Topic>) : DetailCourseState()

    data class GetRole constructor(val role: String) : DetailCourseState()

    data class GenerateCertificatePayment constructor(val url: String) : DetailCourseState()

    data class GenerateRecordPayment constructor(val url: String) : DetailCourseState()

    data class MyFiles constructor(val files: List<FileItem>) : DetailCourseState()

    data class MyCertificate constructor(val certificate: FileItem) : DetailCourseState()

    data class MyRecord constructor(val record: FileItem) : DetailCourseState()

    data class DownloadFilePDF constructor(val file: DownloadFile) : DetailCourseState()

    data class CheckFiles constructor(val checkAvailableFiles: CheckAvailableFiles) :
        DetailCourseState()
}
