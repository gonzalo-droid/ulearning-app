package com.ulearning.ulearning_app.presentation.features.profile

import android.view.View
import androidx.fragment.app.viewModels
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.dateFormat
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.startNewActivity
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.FragmentProfileBinding
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.auth.LoginActivity
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

        with(binding){
            headerInclude.nameText.text = data.name
            nameInputText.editText!!.setText(data.firstName)
            lastNameInpuntText.editText!!.setText(data.lastName)
            emailInpuntText.editText!!.setText(data.email)
            phoneInpuntText.editText!!.setText(data.phone)
            numberDocumentText.text = data.documentNumber

            val date = if(data.dateOfBirth.isNullOrEmpty()) "" else data.dateOfBirth.dateFormat(Config.DATE_FORMAT_TWO).dateFormat(Config.DATE_FORMAT_FIFTEEN)

            dateOfBirthdayeInpuntText.editText!!.setText(date)
        }
    }

    override fun logout() {
        closeLoadingDialog()
        requireContext().startNewActivity<LoginActivity>()
        requireActivity().finishAffinity()
    }

}