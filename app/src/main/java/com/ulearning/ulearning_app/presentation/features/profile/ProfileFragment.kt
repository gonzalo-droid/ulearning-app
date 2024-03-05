package com.ulearning.ulearning_app.presentation.features.profile

import android.app.DatePickerDialog
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.facebook.login.LoginManager
import com.google.android.material.textfield.TextInputLayout
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.dateFormat
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.startNewActivity
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.data.remote.entities.request.ProfileRequest
import com.ulearning.ulearning_app.databinding.FragmentProfileBinding
import com.ulearning.ulearning_app.domain.model.Profile
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.features.auth.LoginActivity
import com.ulearning.ulearning_app.presentation.features.payment.PaymentActivity
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import kotlin.reflect.KMutableProperty0

@AndroidEntryPoint
class ProfileFragment :
    BaseFragmentWithViewModel<FragmentProfileBinding, ProfileViewModel>(),
    ProfileViewState {
    override val binding: FragmentProfileBinding by dataBinding(FragmentProfileBinding::inflate)

    override val viewModel: ProfileViewModel by viewModels()

    override val dataBindingViewModel = BR.profileViewModel

    var profile: ProfileRequest = ProfileRequest()

    private var datePickerDialog: DatePickerDialog? = null


    override fun onViewIsCreated(view: View) {
        ProfileReducer.instance(viewState = this)
        binding.paymentBtn.visibility = View.VISIBLE
        setCalendar()
        observeUiStates()
    }

    private fun setCalendar() {
        val calendar = Calendar.getInstance()

        datePickerDialog = DatePickerDialog(
            this.requireContext(),
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate =
                    String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year)
                profile.dateOfBirth = String.format("%04d-%02d-%02d",year, monthOfYear + 1, dayOfMonth)
                binding.dateOfBirthdayInputText.editText?.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
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

    override fun messageSuccess() {
        Toast.makeText(requireContext(),getString(R.string.success_message),Toast.LENGTH_SHORT).show()
    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun getProfile(data: Profile) {
        closeLoadingDialog()


        with(binding) {
            headerInclude.nameText.text = data.name
            profile.let {
                it.firstName = data.firstName
                it.lastName = data.lastName
                it.name = data.name
                it.secondLastName = data.secondLastName
                it.phone = data.phone
                it.phoneCode = data.phoneCode
                it.documentType = data.documentType
                it.documentNumber = data.documentNumber
                it.dateOfBirth = data.dateOfBirth
                it.email = data.email
                it.gender = data.gender
                it.id = data.id
                it.role = data.role
            }

            nameInputText.editText?.setText(data.firstName)
            lastNameInputText.editText?.setText(data.lastName)
            lastNameSecondInputText.editText?.setText(data.secondLastName)
            phoneInputText.editText?.setText(data.phone)
            numberDocumentInputText.editText?.setText(data.documentNumber)

            val options = arrayListOf("DNI", "CE")
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, options)
            itemAutocomplete.setAdapter(adapter)
            itemAutocomplete.setText(options[0], false)
            profile.documentType = options[0]
            itemAutocomplete.setOnItemClickListener { _, _, position, _ ->
                profile.documentType = options[position]
            }

            setListener()

            val date = if (data.dateOfBirth.isNullOrEmpty()) {
                ""
            } else {
                data.dateOfBirth.dateFormat(Config.DATE_FORMAT_TWO)
                    .dateFormat(Config.DATE_FORMAT_FIFTEEN)
            }

            dateOfBirthdayInputText.editText?.setText(date)


            dateOfBirthdayInputText.setEndIconOnClickListener { datePickerDialog!!.show() }

            tvDate.setOnClickListener {
                datePickerDialog!!.show()
            }
            /*
             ImageLoaderGlide().loadImage(
                 imageView = binding.headerInclude.imageView,
                 imagePath =data.avatar,
                 requestOptions = RequestOptions.centerCropTransform(),
                 placeHolder = R.drawable.ic_profile,
             )
             */

            if (data.role.equals(Config.ROLE_TEACHER)) {
                paymentBtn.visibility = View.GONE
            }

            updateBtn.setOnClickListener {
                viewModel.updatedProfile(profile)
            }

        }
    }

    private fun setListener() {
        with(binding) {
            setupTextInputListener(nameInputText, profile::firstName)
            setupTextInputListener(lastNameInputText, profile::lastName)
            setupTextInputListener(lastNameSecondInputText, profile::secondLastName)
            setupTextInputListener(phoneInputText, profile::phone)
            setupTextInputListener(numberDocumentInputText, profile::documentNumber)
        }
    }

    private fun setupTextInputListener(
        inputLayout: TextInputLayout,
        property: KMutableProperty0<String?>
    ) {
        inputLayout.editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(editable: Editable?) {
                property.set(editable.toString().trim())
                Log.d("watchString", editable.toString())
            }
        })
    }

    override fun goToWebView(url: String) {
        startActivity(
            Intent(requireActivity(), PaymentActivity::class.java).apply {}
        )
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
