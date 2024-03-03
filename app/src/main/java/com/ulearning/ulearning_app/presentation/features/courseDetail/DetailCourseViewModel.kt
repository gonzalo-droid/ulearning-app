package com.ulearning.ulearning_app.presentation.features.courseDetail

import android.util.Log
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.domain.model.*
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetRoleUseCase
import com.ulearning.ulearning_app.domain.useCase.auth.GetTokenUseCase
import com.ulearning.ulearning_app.domain.useCase.courses.*
import com.ulearning.ulearning_app.domain.useCase.topic.GetTopicUseCase
import com.ulearning.ulearning_app.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailCourseViewModel
@Inject constructor(
    private val getTopicUseCase: GetTopicUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val getRoleUseCase: GetRoleUseCase,
    private val getMyFilesUseCase: GetMyFilesUseCase,
    private val checkAvailableFilesUseCase: GetCheckAvailableFilesUseCase,
    private val downloadFileUseCase: GetDownloadFileUseCase,
    private val getMyCertificateUseCase: GetMyCertificateUseCase,
    private val getMyRecordUseCase: GetMyRecordUseCase,
) : BaseViewModel<DetailCourseEvent, DetailCourseState, DetailCourseEffect>() {

    lateinit var course: Course
    lateinit var subscription: Subscription
    var urlWebView: String = ""
    var typeRole: String = ""
    var withCertificate: Boolean = false
    var withRecord: Boolean = false

    lateinit var certificate: FileItem
    lateinit var record: FileItem

    override fun createInitialState(): DetailCourseState {
        return DetailCourseState.Idle
    }

    override fun handleEvent(event: DetailCourseEvent) {
        when (event) {
            DetailCourseEvent.DataDetailCourseClicked -> getDetailCourse()
            DetailCourseEvent.GetTopic -> getTopic()
            DetailCourseEvent.SendComment -> {}
            DetailCourseEvent.GoToConversation -> goToConversation()
            DetailCourseEvent.GetToken -> getToken()
            DetailCourseEvent.GetRole -> getRole()
            DetailCourseEvent.GetCheckAvailableFiles -> getCheckAvailableFiles()
            DetailCourseEvent.GetDownloadCertificate -> downloadCertificatePDF()
            DetailCourseEvent.GetDownloadRecord -> downloadRecordPDF()
            DetailCourseEvent.GetMyFiles -> getMyFiles()
            DetailCourseEvent.GoToCertificate -> goCertificate()
            DetailCourseEvent.GoToRecord -> goRecord()
        }
    }

    private fun goRecord() {
        if (withRecord) {
            generateRecord()
            return
        }
        generateCertificatePayment()
    }

    private fun generateRecord() {
        setState { DetailCourseState.Loading }
        if (::subscription.isInitialized) {
            getMyRecordUseCase(
                GetMyRecordUseCase.Params(subscriptionId = subscription.id!!)
            ) {
                it.either(::handleFailure, ::handleMyRecord)
            }
        }
    }

    private fun goCertificate() {
        if (withCertificate) {
            generateCertificate()
            return
        }
        generateCertificatePayment()
    }

    private fun generateCertificatePayment() {
        if (::subscription.isInitialized) {
            setState { DetailCourseState.GenerateCertificatePayment(url = urlWebView) }
        }
    }

    private fun generateCertificate() {
        setState { DetailCourseState.Loading }
        if (::subscription.isInitialized) {
            getMyCertificateUseCase(
                GetMyCertificateUseCase.Params(subscriptionId = subscription.id!!)
            ) {
                it.either(::handleFailure, ::handleMyCertificate)
            }
        }
    }

    private fun downloadCertificatePDF() {
        if (::certificate.isInitialized) {
            downloadFileUseCase(
                GetDownloadFileUseCase.Params(hash = certificate.hash!!)
            ) {
                it.either(::handleFailure, ::handleFilePDF)
            }
        }
    }

    private fun downloadRecordPDF() {
        if (::record.isInitialized) {
            downloadFileUseCase(
                GetDownloadFileUseCase.Params(hash = record.hash!!)
            ) {
                it.either(::handleFailure, ::handleFilePDF)
            }
        }
    }

    private fun getCheckAvailableFiles() {
        if (::subscription.isInitialized) {
            checkAvailableFilesUseCase(
                GetCheckAvailableFilesUseCase.Params(subscriptionId = subscription.id!!)
            ) {
                it.either(::handleFailure, ::handleCheckFiles)
            }
        }
    }

    private fun getMyFiles() {
        if (::subscription.isInitialized) {
            getMyFilesUseCase(
                GetMyFilesUseCase.Params(subscriptionId = subscription.id!!)
            ) {
                it.either(::handleFailureNoShow, ::handleMyFiles)
            }
        }
    }

    private fun getRole() = getRoleUseCase(BaseUseCase.None()) {
        it.either(::handleFailure, ::handleRole)
    }

    private fun goToConversation() {
        if (::course.isInitialized) {
            setEffect { course.id?.let { DetailCourseEffect.GoToConversation(courseId = it) }!! }
        }
    }

    private fun getTopic() {
        setState { DetailCourseState.Loading }

        if (::course.isInitialized) {
            getTopicUseCase(
                GetTopicUseCase.Params(courseId = course.id!!)
            ) {
                it.either(::handleFailure, ::handleTopics)
            }
        }
    }

    private fun getToken() {
        getTokenUseCase(
            BaseUseCase.None()
        ) {
            it.either(::handleFailure, ::handleToken)
        }
    }

    private fun handleRole(role: String) {
        setState { DetailCourseState.GetRole(role = role) }
    }

    private fun handleToken(url: String) {
        // TODO() REMOVER AL ENVIAR A PROD
        Log.d("urlWebView token",url)
        val sandbox = url.replace("student.ulearning","sandbox.student.ulearning")
        Log.d("urlWebView sandbox",sandbox)

        urlWebView = sandbox
    }

    private fun getDetailCourse() {
        setState { DetailCourseState.Loading }
    }

    private fun handleFailure(failure: Failure) {
        setEffect { DetailCourseEffect.ShowMessageFailure(failure = failure) }
    }

    private fun handleFailureNoShow(failure: Failure) {}

    private fun handleTopics(topics: List<Topic>) {
        setState { DetailCourseState.ListTopic(topics = topics) }
    }

    private fun handleCheckFiles(checkAvailableFiles: CheckAvailableFiles) {
        setState { DetailCourseState.CheckFiles(checkAvailableFiles = checkAvailableFiles) }
    }

    private fun handleMyFiles(files: List<FileItem>) {
        setState { DetailCourseState.MyFiles(files = files) }
    }

    private fun handleMyCertificate(certificate: FileItem) {

        this.certificate = certificate

        setState { DetailCourseState.MyCertificate(certificate = certificate) }
    }

    private fun handleMyRecord(record: FileItem) {

        this.record = record

        setState { DetailCourseState.MyRecord(record = record) }
    }

    private fun handleFilePDF(file: DownloadFile) {

        setState { DetailCourseState.DownloadFilePDF(file = file) }
    }

    companion object Events {
        val goToConversation = DetailCourseEvent.GoToConversation
        val goToCertificate = DetailCourseEvent.GoToCertificate
        val goToRecord = DetailCourseEvent.GoToRecord
    }
}
