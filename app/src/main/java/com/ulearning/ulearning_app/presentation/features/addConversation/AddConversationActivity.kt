package com.ulearning.ulearning_app.presentation.features.addConversation

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putInt
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityAddConversationBinding
import com.ulearning.ulearning_app.databinding.ActivitySearchBinding
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationActivity
import com.ulearning.ulearning_app.presentation.features.search.SearchReducer
import com.ulearning.ulearning_app.presentation.features.search.SearchViewModel
import com.ulearning.ulearning_app.presentation.features.search.SearchViewState
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign

class AddConversationActivity : BaseActivityWithViewModel<ActivityAddConversationBinding, AddConversationViewModel>(),
    AddConversationViewState {
    override val binding: ActivityAddConversationBinding by dataBinding(ActivityAddConversationBinding::inflate)

    override val viewModel: AddConversationViewModel by viewModels()

    override val dataBindingViewModel = BR.addConversationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AddConversationReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            onBackPressed()
        }

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.let {
            viewModel.courseId = Config.COURSE_ID_PUT putInt this@AddConversationActivity
        }

        binding.searchAutocomplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(ed: Editable?) {
                /*if (binding.searchAutocomplete.text.length >= 2) {
                    viewModel.searchUser(binding.searchAutocomplete.text.toString().trim())
                }*/
            }
        })


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
        val adapterUser: ArrayAdapter<User> = ArrayAdapter<User>(
            this, R.layout.simple_dropdown_item_1line, users
        )

        /* val adapterUser = SearchUserAdapter(
             users = users,
             context = this@AddConversationActivity,
             resource = R.layout.item_user
         )*/
     /*   binding.searchAutocomplete.setAdapter(adapterUser)

        binding.searchAutocomplete.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.user = adapterView.getItemAtPosition(i) as User
        }*/
    }
}