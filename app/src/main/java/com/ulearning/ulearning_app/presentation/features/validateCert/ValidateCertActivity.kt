package com.ulearning.ulearning_app.presentation.features.validateCert

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.activity.viewModels
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.*
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityValidateCertBinding
import com.ulearning.ulearning_app.domain.model.DownloadFile
import com.ulearning.ulearning_app.domain.model.FileItem
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ValidateCertActivity :
    BaseActivityWithViewModel<ActivityValidateCertBinding, ValidateCertViewModel>(),
    ValidateCertViewState {
    override val binding: ActivityValidateCertBinding by dataBinding(ActivityValidateCertBinding::inflate)

    override val viewModel: ValidateCertViewModel by viewModels()

    override val dataBindingViewModel = BR.validateCertViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ValidateCertReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            finish()
        }

        observeUiStates()
    }

    private fun observeUiStates() {
        binding.cardInfo.visibility = View.GONE

        viewModel.let {
            val value =
                Config.PARAM_NAME_PUT putString this@ValidateCertActivity

            binding.numberCertTl.editText?.setText(value)
        }

        viewModel.apply {
            lifecycleScopeCreate(activity = this@ValidateCertActivity, method = {
                state.collect { state ->
                    ValidateCertReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@ValidateCertActivity, method = {
                effect.collect { effect ->
                    ValidateCertReducer.selectEffect(effect)
                }
            })
        }
    }

    override fun downloadFilePDF(file: DownloadFile) {
        closeLoadingDialog()
        try {
            val uri = Uri.parse(file.fileUrl)

            val request =
                DownloadManager.Request(uri)
                    .setTitle("U-Learning Pdf")
                    .setDescription("Descargando...")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                    .setVisibleInDownloadsUi(true)
                    .setDestinationInExternalFilesDir(
                        this,
                        Environment.DIRECTORY_DOCUMENTS,
                        file.filename + ".pdf",
                    )
            val dm = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
        } catch (e: Exception) {
            messageFailure(Failure.DefaultError(R.string.error_download_pdf))
        }
    }

    override fun messageFailure(failure: Failure) {
        closeLoadingDialog()

        binding.cardInfo.visibility = View.GONE

        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun validateCert(file: FileItem) {
        closeLoadingDialog()
        with(binding) {
            cardInfo.visibility = View.VISIBLE
            subTitleTv.text = getString(R.string.subTile_card_course, file.fileInfo?.title)
            numberCertTv.text = getString(R.string.item_number_cert, file.name)
            nameTv.text = getString(R.string.item_name, file.fileInfo?.name)
            scoreTv.text = getString(R.string.item_score, file.fileInfo?.rating.toString())
            hourTv.text = getString(R.string.item_hour, file.fileInfo?.hours)
        }
    }
}
