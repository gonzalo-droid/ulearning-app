package com.ulearning.ulearning_app.presentation.features.addConversation

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putInt
import com.ulearning.ulearning_app.core.extensions.putString
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityAddConversationBinding
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationActivity
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationAdapter
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddConversationActivity :
    BaseActivityWithViewModel<ActivityAddConversationBinding, AddConversationViewModel>(),
    AddConversationViewState {
    override val binding: ActivityAddConversationBinding by dataBinding(
        ActivityAddConversationBinding::inflate
    )

    override val viewModel: AddConversationViewModel by viewModels()

    override val dataBindingViewModel = BR.addConversationViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: UserChipAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AddConversationReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)


        observeUiStates()
    }

    private fun observeUiStates() {


        viewModel.let {
            viewModel.courseId = Config.COURSE_ID_PUT putInt this@AddConversationActivity
            viewModel.textUserIds = Config.LIST_USER_IDS_PUT putString this@AddConversationActivity
            viewModel.textUserIds.trim().removeSuffix(",").split(",").forEach {
                viewModel.listUserIds.add(it.toInt())
            }

            viewModel.setEvent(AddConversationEvent.GetUsersClick)
        }




        viewModel.apply {
            lifecycleScopeCreate(activity = this@AddConversationActivity, method = {
                state.collect { state ->
                    AddConversationReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@AddConversationActivity, method = {
                effect.collect { effect ->
                    AddConversationReducer.selectEffect(effect)
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

    override fun conversation(conversation: Conversation) {
        closeLoadingDialog()
        startActivity(Intent(this, ConversationActivity::class.java).apply {
            putExtra(Config.COURSE_ID_PUT, viewModel.courseId)
        })
        finish()
    }

    override fun users(users: List<User>) {
        closeLoadingDialog()

        adapter = UserChipAdapter(users = users)

        recycler.adapter = adapter
    }
}