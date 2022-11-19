package com.ulearning.ulearning_app.presentation.features.courses


object DetailCourseReducer {

    private lateinit var viewState: DetailCourseViewState

    fun instance(viewState: DetailCourseViewState) {
        this.viewState = viewState
    }

    fun selectState(state: DetailCourseState) {
        when (state) {
            is DetailCourseState.Idle -> {}

            is DetailCourseState.Loading -> viewState.loading()

            is DetailCourseState.DataDetailCourse -> {}

            is DetailCourseState.ListTopic -> {
                viewState.getTopics(topics = state.topics)
            }
            is DetailCourseState.CheckFiles -> viewState.checkAvailableFiles(checkAvailableFiles = state.checkAvailableFiles)
            is DetailCourseState.MyFiles -> viewState.myFiles(files = state.files)
            is DetailCourseState.DownloadFilePDF -> viewState.downloadFilePDF(file = state.file)
            is DetailCourseState.GetRole -> viewState.getRole(role = state.role)

            is DetailCourseState.GenerateCertificatePayment -> viewState.generateCertificatePayment(
                url = state.url
            )
            is DetailCourseState.GenerateRecordPayment -> viewState.generateCertificatePayment(
                url = state.url
            )

            is DetailCourseState.MyCertificate -> viewState.myCertificate(certificate = state.certificate)
            is DetailCourseState.MyRecord -> viewState.myRecord(record = state.record)
        }
    }

    fun selectEffect(effect: DetailCourseEffect) {
        when (effect) {
            is DetailCourseEffect.ShowMessageFailure -> viewState.messageFailure(effect.failure)
            is DetailCourseEffect.GoToConversation -> viewState.goToConversation(effect.courseId)
        }
    }
}