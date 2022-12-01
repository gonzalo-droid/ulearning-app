package com.ulearning.ulearning_app.presentation.features.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.facebook.login.LoginManager
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
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
import com.ulearning.ulearning_app.presentation.features.conversation.ConversationActivity
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
        binding.paymentBtn.visibility = View.VISIBLE
        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(ProfileEvent.DataProfileClicked)

        binding.scanQrBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_profile_to_scanQrActivity,
            )
        }

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

        with(binding) {
            headerInclude.nameText.text = data.name
            nameInputText.editText!!.setText(data.firstName)
            lastNameInputText.editText!!.setText(data.lastName)
            lastNameMotherInputText.editText!!.setText(data.secondLastName)
            emailInputText.editText!!.setText(data.email)
            phoneInputText.editText!!.setText(data.phone)
            numberDocumentText.text = data.documentNumber

            val date =
                if (data.dateOfBirth.isNullOrEmpty()) ""
                else data.dateOfBirth.dateFormat(Config.DATE_FORMAT_TWO)
                    .dateFormat(Config.DATE_FORMAT_FIFTEEN)

            dateOfBirthdayInputText.editText!!.setText(date)


/*            val imageUri: String? =
                if (benefits.get(position).imagesUrl.isNotEmpty())
                    benefits[position].imagesUrl[0] else null

            Picasso.with(itemView.context).load(imageUri).fit().centerCrop()
                .placeholder(R.drawable.benefits_placeholder)
                .error(R.drawable.benefits_placeholder)
                .into(binding.imageView)*/

            if (data.role.equals(Config.ROLE_TEACHER)) {
                paymentBtn.visibility = View.GONE
            }
        }
    }

    override fun goToWebView(url: String) {

        val urlPayment = "${url}?return=/payments"
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlPayment)))
    }

    override fun logout() {
        LoginManager.getInstance().logOut()
        
        closeLoadingDialog()
        requireContext().startNewActivity<LoginActivity>()
        requireActivity().finishAffinity()
    }

    override fun scanQr() {

    }

}