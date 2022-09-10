package com.ulearning.ulearning_app.presentation.features.courses

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.html
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.FragmentDetailCourseBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.topic.TopicAdapter
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailCourseFragment :
    BaseFragmentWithViewModel<FragmentDetailCourseBinding, DetailCourseViewModel>(),
    DetailCourseViewState {


    override val binding: FragmentDetailCourseBinding by dataBinding(FragmentDetailCourseBinding::inflate)

    override val viewModel: DetailCourseViewModel by viewModels()

    override val dataBindingViewModel = BR.detailCourseViewModel

    private lateinit var subscription: Subscription

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: DetailCourseTeacherAdapter

    private lateinit var topicRecycler: RecyclerView

    private lateinit var topicAdapter: TopicAdapter


    override fun onViewIsCreated(view: View) {

        DetailCourseReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        topicRecycler = binding.topicRecycler

        topicRecycler.layoutManager = LinearLayoutManager(requireActivity())

        observeUiStates()
    }

    private fun observeUiStates() {

        subscription = requireArguments().getSerializable(Config.SUBSCRIPTION_PUT) as Subscription

        viewModel.courseId = subscription.course_id!!

        setDetailCourse(subscription)

        viewModel.setEvent(DetailCourseEvent.GoToTopic)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    DetailCourseReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    DetailCourseReducer.selectEffect(effect)
                }
            })
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

    override fun getTopics(topics: List<Topic>) {

        closeLoadingDialog()

        val mutableTopics : MutableList<Topic> = mutableListOf()


        topics.forEach {

            mutableTopics.add(it)

            it.children!!.reversed().forEach { child ->
                mutableTopics.add(child)
            }
        }


        topicAdapter = TopicAdapter(topics = mutableTopics)

        topicRecycler.adapter = topicAdapter
    }
/*    override fun goTopic() {

        findNavController().navigate(
            R.id.action_detailCourseFragment_to_topicFragment,
            Bundle().apply {
                putInt(Config.COURSE_ID_PUT, courseId)
                putSerializable(Config.COURSE, course)
            }
        )

    }*/

    private fun setDetailCourse(data: Subscription) {
        with(binding) {

            topBarInclude.title = data.course!!.title
            titleText.text = data.course!!.title
            descriptionText.text = data.course!!.descriptionShort!!.html()
            timeText.text = data.course!!.formatAsynchronousHour()
            modalityText.text = data.course!!.formatModality()
            topicText.text = data.course!!.lessonsCount.toString()

            adapter = DetailCourseTeacherAdapter(teachers = data.group!!.teachers!!)

            recycler.adapter = adapter
        }
    }
}