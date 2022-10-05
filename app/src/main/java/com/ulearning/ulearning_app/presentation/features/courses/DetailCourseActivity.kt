package com.ulearning.ulearning_app.presentation.features.courses

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.html
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putSubscription
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityDetailCourseBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationActivity
import com.ulearning.ulearning_app.presentation.features.courses.adapter.DetailCourseTeacherAdapter
import com.ulearning.ulearning_app.presentation.features.courses.adapter.TopicAdapter
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
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

        viewModel.let {
            viewModel.subscription = Config.SUBSCRIPTION_PUT putSubscription this@DetailCourseActivity
        }

        setDetailCourse(viewModel.subscription)

        viewModel.setEvent(DetailCourseEvent.GetTopic)

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


        topicAdapter = TopicAdapter(topics = mutableTopics)

        topicRecycler.adapter = topicAdapter
    }

    override fun goToConversation(courseId: Int) {
        startActivity(Intent(this, ConversationActivity::class.java).apply {
            putExtra(Config.COURSE_ID_PUT, viewModel.subscription.courseId)
        })
    }

    private fun setDetailCourse(data: Subscription) {
        with(binding) {
            topBarInclude.title = data.course?.title
            titleText.text = data.course?.title

            descriptionText.text = data.course?.descriptionShort?.html()
            timeText.text = data.course?.formatAsynchronousHour()
            modalityText.text = data.course?.formatModality()
            topicText.text = data.course?.lessonsCount.toString()
            adapter = DetailCourseTeacherAdapter(teachers = data.group?.teachers?: arrayListOf())
            recycler.adapter = adapter
        }
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