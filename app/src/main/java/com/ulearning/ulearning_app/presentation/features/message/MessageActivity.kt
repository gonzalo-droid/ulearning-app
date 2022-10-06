package com.ulearning.ulearning_app.presentation.features.message

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putConversation
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityMessageBinding
import com.ulearning.ulearning_app.domain.model.Message
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MessageActivity :
    BaseActivityWithViewModel<ActivityMessageBinding, MessageViewModel>(),
    MessageViewState {

    override val binding: ActivityMessageBinding by dataBinding(ActivityMessageBinding::inflate)

    override val viewModel: MessageViewModel by viewModels()

    override val dataBindingViewModel = BR.messageViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MessageReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(this)

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.let {
            viewModel.conversation = Config.CONVERSATION_PUT putConversation this@MessageActivity

            viewModel.setEvent(MessageEvent.MessagesClicked)
        }

        viewModel.apply {
            lifecycleScopeCreate(activity = this@MessageActivity, method = {
                state.collect { state ->
                    MessageReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@MessageActivity, method = {
                effect.collect { effect ->
                    MessageReducer.selectEffect(effect)
                }
            })
        }

    }


    override fun messages(messages: List<Message>) {
        closeLoadingDialog()

        adapter =
            MessageAdapter(messages = messages.reversed(), conversation = viewModel.conversation)

        recycler.adapter = adapter

        adapter.notifyDataSetChanged()

        recycler.scrollToPosition(messages.size - 1);

        recycler.scrollToPosition(adapter.itemCount - 1);

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