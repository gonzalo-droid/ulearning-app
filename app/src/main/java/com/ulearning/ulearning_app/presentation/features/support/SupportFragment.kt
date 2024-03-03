package com.ulearning.ulearning_app.presentation.features.support

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.FragmentSupportBinding
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationAdapter
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupportFragment :
    BaseFragmentWithViewModel<FragmentSupportBinding, SupportViewModel>(),
    SupportViewState {

    override val binding: FragmentSupportBinding by dataBinding(FragmentSupportBinding::inflate)

    override val viewModel: SupportViewModel by viewModels()

    override val dataBindingViewModel = BR.supportViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: ConversationAdapter

    override fun onViewIsCreated(view: View) {

        SupportReducer.instance(viewState = this)

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(requireContext())

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.setEvent(SupportEvent.ConversationsClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    SupportReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    SupportReducer.selectEffect(effect)
                }
            })
        }
    }

    override fun conversations(conversations: List<Conversation>) {
        closeLoadingDialog()

        adapter = ConversationAdapter(conversations = conversations) { conversation ->

            onItemSelected(conversation)
        }

        recycler.adapter = adapter
    }

    override fun newConversation() {

        findNavController().navigate(
            R.id.action_navigation_support_to_addConversationActivity,
            Bundle().apply {
                putSerializable(Config.COURSE_ID_PUT, viewModel.courseId)
                putSerializable(Config.TYPE_MESSAGE, Config.MESSAGE_SUPPORT)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.setEvent(SupportEvent.ConversationsClicked)
    }

    private fun onItemSelected(conversation: Conversation) {

        findNavController().navigate(
            R.id.action_navigation_support_to_messageActivity,
            Bundle().apply {
                putSerializable(Config.CONVERSATION_PUT, conversation)
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
