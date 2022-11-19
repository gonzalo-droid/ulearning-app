package com.ulearning.ulearning_app.presentation.features.courses

import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.CheckAvailableFiles
import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.model.Topic

interface DetailCourseViewState {

    fun messageFailure(failure: Failure)

    fun loading()

    fun getTopics(topics: List<Topic>)

    fun goToConversation(courseId: Int)

    fun getRole(role: String)

    fun myFiles(files: List<FileItem>)

    fun myCertificate(certificate: FileItem)

    fun myRecord(record: FileItem)

    fun generateCertificatePayment(url: String)

    fun checkAvailableFiles(checkAvailableFiles: CheckAvailableFiles)

    fun downloadFile(downloadFile: DownloadFile)

    fun certificatePDF(file: DownloadFile)

}