package com.ulearning.ulearning_app.presentation.features.topic

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.databinding.FragmentTopicBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicFragment :
    BaseFragmentWithViewModel<FragmentTopicBinding, TopicViewModel>(),
    TopicViewState {


    override val binding: FragmentTopicBinding by dataBinding(FragmentTopicBinding::inflate)

    override val viewModel: TopicViewModel by viewModels()

    override val dataBindingViewModel = BR.topicViewModel

    private lateinit var subscription: Subscription

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: TopicAdapter

    override fun onViewIsCreated(view: View) {

        TopicReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        observeUiStates()
    }

    private fun observeUiStates() {


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
        TODO("Not yet implemented")
    }
}