package com.ulearning.ulearning_app.presentation.features.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putInt
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivitySearchBinding
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.addConversation.AddConversationActivity
import com.ulearning.ulearning_app.presentation.features.message.*
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity :
    BaseActivityWithViewModel<ActivitySearchBinding, SearchViewModel>(),
    SearchViewState {

    override val binding: ActivitySearchBinding by dataBinding(ActivitySearchBinding::inflate)

    override val viewModel: SearchViewModel by viewModels()

    override val dataBindingViewModel = BR.searchViewModel

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SearchReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(this)

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.let {
            viewModel.courseId = Config.COURSE_ID_PUT putInt this@SearchActivity
        }

        viewModel.setEvent(SearchEvent.GetUsersClick)

        binding.searchEdit.addTextChangedListener { filter ->
            with(viewModel) {
                if (listUser.isNotEmpty()) {
                    val newUser = listUser.filter { user ->
                        user.name!!.lowercase().contains(filter.toString().lowercase())
                    }
                    adapter.updateUser(newUser)
                }
            }
        }

        viewModel.apply {
            lifecycleScopeCreate(activity = this@SearchActivity, method = {
                state.collect { state ->
                    SearchReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@SearchActivity, method = {
                effect.collect { effect ->
                    SearchReducer.selectEffect(effect)
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

    override fun users(users: List<User>) {
        closeLoadingDialog()

        viewModel.listUser = users

        adapter = UserAdapter(users = users) { user ->

            onItemSelected(user)
        }

        recycler.adapter = adapter
    }

    private fun onItemSelected(user: User) {
        startActivity(
            Intent(this, AddConversationActivity::class.java).apply {
                putExtra(Config.COURSE_ID_PUT, viewModel.courseId)
                putExtra(Config.LIST_USER_IDS_PUT, "${user.id},")
                putExtra(Config.TYPE_MESSAGE, Config.MESSAGE_COURSE)
            }
        )
    }
}
