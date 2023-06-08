package com.ulearning.ulearning_app.presentation.features.conversation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putInt
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityConversationBinding
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.message.MessageActivity
import com.ulearning.ulearning_app.presentation.features.search.SearchActivity
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ConversationActivity :
    BaseActivityWithViewModel<ActivityConversationBinding, ConversationViewModel>(),
    ConversationViewState {

    override val binding: ActivityConversationBinding by dataBinding(ActivityConversationBinding::inflate)

    override val viewModel: ConversationViewModel by viewModels()

    override val dataBindingViewModel = BR.conversationViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: ConversationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ConversationReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            finish()
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(this)

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.let {
            viewModel.courseId = Config.COURSE_ID_PUT putInt this@ConversationActivity
        }

        viewModel.setEvent(ConversationEvent.ConversationsClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@ConversationActivity, method = {
                state.collect { state ->
                    ConversationReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@ConversationActivity, method = {
                effect.collect { effect ->
                    ConversationReducer.selectEffect(effect)
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
        startActivity(Intent(this, SearchActivity::class.java).apply {
            putExtra(Config.COURSE_ID_PUT, viewModel.courseId)
            putExtra(Config.TYPE_MESSAGE, Config.MESSAGE_COURSE)
        })
        finish()
    }

    private fun onItemSelected(conversation: Conversation) {
        startActivity(Intent(this, MessageActivity::class.java).apply {
            putExtra(Config.CONVERSATION_PUT, conversation)
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