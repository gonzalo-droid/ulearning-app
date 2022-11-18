package com.ulearning.ulearning_app.presentation.features.courses

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.*
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityDetailCourseBinding
import com.ulearning.ulearning_app.domain.model.*
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.addConversation.AddConversationActivity
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationActivity
import com.ulearning.ulearning_app.presentation.features.courses.adapter.DetailCourseTeacherAdapter
import com.ulearning.ulearning_app.presentation.features.courses.adapter.TopicAdapter
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class DetailCourseActivity :
    BaseActivityWithViewModel<ActivityDetailCourseBinding, DetailCourseViewModel>(),
    DetailCourseViewState {

    override val binding: ActivityDetailCourseBinding by dataBinding(ActivityDetailCourseBinding::inflate)

    override val viewModel: DetailCourseViewModel by viewModels()

    override val dataBindingViewModel = BR.detailCourseViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: DetailCourseTeacherAdapter

    private lateinit var topicRecycler: RecyclerView

    private lateinit var topicAdapter: TopicAdapter

    private lateinit var certificate: FileItem

    private lateinit var record: FileItem

    private lateinit var checkFiles: CheckAvailableFiles


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailCourseReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        topicRecycler = binding.topicRecycler

        topicRecycler.layoutManager = LinearLayoutManager(this)

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.apply {
            lifecycleScopeCreate(activity = this@DetailCourseActivity, method = {
                state.collect { state ->
                    DetailCourseReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@DetailCourseActivity, method = {
                effect.collect { effect ->
                    DetailCourseReducer.selectEffect(effect)
                }
            })
        }

        viewModel.let {
            viewModel.course =
                Config.COURSE_PUT putCourse this@DetailCourseActivity

            viewModel.subscription =
                Config.SUBSCRIPTION_PUT putSubscription this@DetailCourseActivity
        }

        viewModel.setEvent(DetailCourseEvent.GetToken)

        viewModel.setEvent(DetailCourseEvent.GetRole)

        viewModel.setEvent(DetailCourseEvent.GetTopic)

        setDetailCourse(viewModel.course)

        setDetailSubscription(viewModel.subscription)

        binding.messageBtn.visibility =
            if (viewModel.subscription.isFinished!!) View.GONE else View.VISIBLE

    }

    private fun setDetailSubscription(subscription: Subscription) {
        with(binding) {

            goTeacher(subscription.group?.teachers ?: arrayListOf())
        }
    }

    override fun getTopics(topics: List<Topic>) {

        closeLoadingDialog()

        val mutableTopics: MutableList<Topic> = mutableListOf()


        topics.forEach {

            mutableTopics.add(it)

            it.children!!.reversed().forEach { child ->
                mutableTopics.add(child)
            }
        }


        topicAdapter = TopicAdapter(topics = mutableTopics) { topic ->
            onItemSelected(topic)
        }

        topicRecycler.adapter = topicAdapter
    }

    private fun onItemSelected(topic: Topic) {
        viewModel.let {
            if (viewModel.urlWebView.isNotEmpty()) {
                val topicUrl = "/courses/${topic.courseId}/topics/${topic.id}"
                val url = "${viewModel.urlWebView}?return=${topicUrl}"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
    }

    override fun goToConversation(courseId: Int) {
        startActivity(Intent(this, ConversationActivity::class.java).apply {
            putExtra(Config.COURSE_ID_PUT, viewModel.course.id)
        })
    }

    override fun getRole(role: String) {
        with(viewModel) {
            typeRole = role
            if (role == Config.ROLE_STUDENT && subscription.isFinished!!) {
                binding.messageBtn.visibility = View.GONE
            }

            if (role == Config.ROLE_STUDENT) {
                setEvent(DetailCourseEvent.GetMyFiles)
                setEvent(DetailCourseEvent.GetCheckAvailableFiles)
                withCertificate =
                    subscription.course?.certificate!! || subscription.purchasedCertificate!!
                withRecord =
                    subscription.course?.record!! || subscription.purchasedRecord!!
            }
        }
    }

    override fun myFiles(files: List<FileItem>) {

        files.forEach {
            if (it.type === "certificate") {
                certificate = it
            }
            if (it.type === "record") {
                record = it;
            }
        }

    }

    override fun myCertificate(certificate: FileItem) {
        viewModel.setEvent(DetailCourseEvent.GetDownloadFile)
    }

    override fun myRecord(record: FileItem) {
        viewModel.setEvent(DetailCourseEvent.GetDownloadFile)
    }

    override fun checkAvailableFiles(checkAvailableFiles: CheckAvailableFiles) {
        checkFiles = checkAvailableFiles

        with(binding) {
            downloadBtn.visibility = if (checkFiles?.certificate!!) View.VISIBLE else View.GONE
            downloadRecordBtn.visibility = if (checkFiles.record!!) View.VISIBLE else View.GONE
        }
    }

    override fun downloadFile(downloadFile: DownloadFile) {
        closeLoadingDialog()
    }

    override fun certificatePDF(file: DownloadFile) {
        closeLoadingDialog()
        try {
            val uri = null
                //Uri.parse(file.fileUrl);

            val request = DownloadManager.Request(uri)
                .setTitle("U-Learning Pdf")
                .setDescription("Descargando...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setVisibleInDownloadsUi(true)
                .setDestinationInExternalFilesDir(
                    this,
                    Environment.DIRECTORY_DOCUMENTS,
                    file.filename + ".pdf"
                );
            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
        } catch (e: Exception) {
            messageFailure(Failure.DefaultError(R.string.error_download_pdf))
        }

    }


    private fun setDetailCourse(data: Course) {
        with(binding) {
            topBarInclude.title = data.title
            titleText.text = data.title

            descriptionText.text = data.descriptionLarge?.html()
            timeText.text = data.formatSelfStudyHour()
            modalityText.text = data.formatModality()
            topicText.text = data.lessonsCount.toString()
        }
    }

    private fun goTeacher(list: List<Teacher>) {
        adapter = DetailCourseTeacherAdapter(teachers = list) { user ->
            onItemUserSelected(user)
        }
        recycler.adapter = adapter
    }

    private fun onItemUserSelected(user: Teacher) {

        startActivity(Intent(this, AddConversationActivity::class.java).apply {
            putExtra(Config.COURSE_ID_PUT, viewModel.course.id)
            putExtra(Config.LIST_USER_IDS_PUT, "${user.id},")
            putExtra(Config.TYPE_MESSAGE, Config.MESSAGE_COURSE)
            putExtra(Config.ROLE, viewModel.typeRole)
        })
    }

    override fun messageFailure(failure: Failure) {
        closeLoadingDialog()

        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        showLoadingDialog()
    }

}