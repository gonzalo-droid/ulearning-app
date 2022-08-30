package com.ulearning.ulearning_app.presentation.features.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.databinding.FragmentHomeBinding
import com.ulearning.ulearning_app.databinding.FragmentProfileBinding
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.HomeEvent
import com.ulearning.ulearning_app.presentation.features.home.HomeReducer
import com.ulearning.ulearning_app.presentation.features.home.HomeViewState
import com.ulearning.ulearning_app.presentation.features.home.viewModel.HomeViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment :
    BaseFragmentWithViewModel<FragmentProfileBinding, ProfileViewModel>(),
    ProfileViewState {

    override val binding: FragmentProfileBinding by dataBinding(FragmentProfileBinding::inflate)

    override val viewModel: ProfileViewModel by viewModels()

    override val dataBindingViewModel = BR.profileViewModel

    override fun onViewIsCreated(view: View) {

        ProfileReducer.instance(viewState = this)

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(ProfileEvent.DataProfileClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    ProfileReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    ProfileReducer.selectEffect(effect)
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

    override fun getProfile(data: Profile) {
        closeLoadingDialog()
        logDebug(data.name)

        with(binding){
            headerInclude.nameText.text = data.name
            nameInputText.editText!!.setText(data.firstName)
            lastNameInpuntText.editText!!.setText(data.lastName)
            emailInpuntText.editText!!.setText(data.email)
            phoneInpuntText.editText!!.setText(data.phone)
            numberDocumentText.text = data.documentNumber
        }
    }

}