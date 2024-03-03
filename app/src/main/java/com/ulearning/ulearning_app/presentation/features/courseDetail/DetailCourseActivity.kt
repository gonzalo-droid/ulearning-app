package com.ulearning.ulearning_app.presentation.features.courseDetail

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.html
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putCourse
import com.ulearning.ulearning_app.core.extensions.putSubscription
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityDetailCourseBinding
import com.ulearning.ulearning_app.domain.model.CheckAvailableFiles
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Teacher
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.addConversation.AddConversationActivity
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationActivity
import com.ulearning.ulearning_app.presentation.features.courseDetail.adapter.DetailCourseTeacherAdapter
import com.ulearning.ulearning_app.presentation.features.courseDetail.adapter.TopicAdapter
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import com.ulearning.ulearning_app.presentation.utils.imageLoader.ImageLoaderGlide
import dagger.hilt.android.AndroidEntryPoint

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

        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val upArrow = resources.getDrawable(R.drawable.ic_arrow_back)

        supportActionBar!!.setHomeAsUpIndicator(upArrow)

        DetailCourseReducer.instance(viewState = this)

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        topicRecycler = binding.topicRecycler

        topicRecycler.layoutManager = LinearLayoutManager(this)

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.let {
            viewModel.course = Config.COURSE_PUT putCourse this@DetailCourseActivity

            viewModel.subscription =
                Config.SUBSCRIPTION_PUT putSubscription this@DetailCourseActivity
        }

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

        viewModel.setEvent(DetailCourseEvent.GetToken)

        viewModel.setEvent(DetailCourseEvent.GetRole)

        viewModel.setEvent(DetailCourseEvent.GetTopic)

        setDetailCourse(viewModel.course)

        setDetailSubscription(viewModel.subscription)
    }

    private fun setDetailSubscription(subscription: Subscription) {

        if (subscription.group?.teachers.isNullOrEmpty()) {
            binding.teacherTitle.visibility = View.GONE
            binding.recycler.visibility = View.GONE
        } else {
            binding.teacherTitle.visibility = View.VISIBLE
            binding.recycler.visibility = View.VISIBLE
            goTeacher(subscription.group?.teachers ?: arrayListOf())
        }
    }

    override fun getTopics(topics: List<Topic>) {

        closeLoadingDialog()

        binding.topicBtn.visibility = if (topics.isEmpty()) View.INVISIBLE else View.VISIBLE

        val mutableTopics: MutableList<Topic> = mutableListOf()

        topics.forEach { it ->

            mutableTopics.add(it)

            it.children!!.forEach { child ->
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
                val url = "${viewModel.urlWebView}?return=$topicUrl"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
    }

    override fun goToConversation(courseId: Int) {
        startActivity(
            Intent(this, ConversationActivity::class.java).apply {
                putExtra(Config.COURSE_ID_PUT, viewModel.course.id)
            }
        )
    }

    override fun getRole(role: String) {
        with(viewModel) {
            typeRole = role
            if (role == Config.ROLE_STUDENT && subscription.isFinished!!) {
                binding.messageBtn.visibility = View.GONE
            }

            if (role == Config.ROLE_STUDENT) {
                setEvent(DetailCourseEvent.GetCheckAvailableFiles)
                setEvent(DetailCourseEvent.GetMyFiles)
                withCertificate =
                    subscription.hasCertificate!! || subscription.purchasedCertificate!!
                withRecord = subscription?.hasCertificate!! || subscription.purchasedRecord!!

                binding.downloadCertText.text =
                    if (!withCertificate) getString(R.string.buy_cert) else getString(R.string.download_cert)
                binding.downloadRecordText.text =
                    if (!withRecord) getString(R.string.buy_record) else getString(R.string.download_record)
            }
        }
    }

    override fun myFiles(files: List<FileItem>) {

        files.forEach {
            if (it.type === "certificate") {
                certificate = it
            }
            if (it.type === "record") {
                record = it
            }
        }
    }

    override fun myCertificate(certificate: FileItem) {
        viewModel.setEvent(DetailCourseEvent.GetDownloadCertificate)
    }

    override fun myRecord(record: FileItem) {
        viewModel.setEvent(DetailCourseEvent.GetDownloadRecord)
    }

    override fun generateCertificatePayment(url: String) {
        with(viewModel) {
            if (urlWebView.isNotEmpty()) {
                val topicUrl = "/courses/${subscription.courseId}"
                val url = "${viewModel.urlWebView}?return=$topicUrl"
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
    }

    override fun checkAvailableFiles(checkAvailableFiles: CheckAvailableFiles) {
        checkFiles = checkAvailableFiles

        with(binding) {
            downloadCertBtn.visibility = if (checkFiles.certificate!!) View.VISIBLE else View.GONE
            downloadRecordBtn.visibility = if (checkFiles.record!!) View.VISIBLE else View.GONE
        }
    }

    override fun downloadFilePDF(file: DownloadFile) {
        closeLoadingDialog()
        try {
            val uri = Uri.parse(file.fileUrl)

            val request = DownloadManager.Request(uri).setTitle("U-Learning Pdf")
                .setDescription("Descargando...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                .setVisibleInDownloadsUi(true).setDestinationInExternalFilesDir(
                    this, Environment.DIRECTORY_DOCUMENTS, file.filename + ".pdf"
                )
            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
        } catch (e: Exception) {
            messageFailure(Failure.DefaultError(R.string.error_download_pdf))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setDetailCourse(data: Course) {
        with(binding) {

            if (!data.mainImage?.originalUrl.isNullOrEmpty()) {
                ImageLoaderGlide().loadImage(
                    imageView = imageCourseIv,
                    imagePath = data.mainImage?.originalUrl!!,
                    requestOptions = RequestOptions.centerCropTransform(),
                    placeHolder = R.drawable.course_test
                )

                imageCourseIv.alpha = 0.5f
            } else {
                imageCourseIv.setImageResource(R.drawable.course_test)
            }

            toolbarLayout.title = " "
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

        startActivity(
            Intent(this, AddConversationActivity::class.java).apply {
                putExtra(Config.COURSE_ID_PUT, viewModel.course.id)
                putExtra(Config.LIST_USER_IDS_PUT, "${user.id},")
                putExtra(Config.TYPE_MESSAGE, Config.MESSAGE_COURSE)
                putExtra(Config.ROLE, viewModel.typeRole)
            }
        )
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
