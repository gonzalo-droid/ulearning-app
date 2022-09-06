package com.ulearning.ulearning_app.presentation.features.courses

import android.view.View
import androidx.fragment.app.viewModels
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.databinding.FragmentDetailCourseBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailCourseFragment :
    BaseFragmentWithViewModel<FragmentDetailCourseBinding, DetailCourseViewModel>(),
    DetailCourseViewState {


    override val binding: FragmentDetailCourseBinding by dataBinding(FragmentDetailCourseBinding::inflate)

    override val viewModel: DetailCourseViewModel by viewModels()

    override val dataBindingViewModel = BR.detailCourseViewModel

    override fun onViewIsCreated(view: View) {


        observeUiStates()
    }

    private fun observeUiStates() {

        data = requireArguments().getSerializable(Config.DATA_VALIDATED_DOCUMENT_PUT) as ValidatedDocument


    }

    override fun messageFailure(failure: Failure) {

    }

    override fun loading() {

    }

    override fun getDataDetailCourse(data: Subscription) {

    }
}