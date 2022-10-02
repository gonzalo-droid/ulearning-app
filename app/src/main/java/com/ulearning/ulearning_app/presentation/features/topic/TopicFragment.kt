package com.ulearning.ulearning_app.presentation.features.topic

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.FragmentTopicBinding
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.courses.adapter.TopicAdapter
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment :
    BaseFragmentWithViewModel<FragmentTopicBinding, TopicViewModel>(),
    TopicViewState {

    // TODO, this fragment and flow is not implemented

    override val binding: FragmentTopicBinding by dataBinding(FragmentTopicBinding::inflate)

    override val viewModel: TopicViewModel by viewModels()

    override val dataBindingViewModel = BR.topicViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: TopicAdapter

    private var courseId: Int = 0

    private lateinit var course : Course

    override fun onViewIsCreated(view: View) {

        TopicReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(requireActivity())

        observeUiStates()
    }

    private fun observeUiStates() {

        courseId = requireArguments().getInt(Config.COURSE_ID_PUT)

        course = requireArguments().getSerializable(Config.COURSE) as Course

        binding.topBarInclude.title = course.title

        viewModel.courseId = courseId

        viewModel.setEvent(TopicEvent.GoToTopic)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    TopicReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    TopicReducer.selectEffect(effect)
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


        adapter = TopicAdapter(topics = mutableTopics)

        recycler.adapter = adapter
    }
}