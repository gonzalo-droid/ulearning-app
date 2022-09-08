package com.ulearning.ulearning_app.presentation.features.courses

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.html
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.FragmentDetailCourseBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseFragmentWithViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailCourseFragment :
    BaseFragmentWithViewModel<FragmentDetailCourseBinding, DetailCourseViewModel>(),
    DetailCourseViewState {


    override val binding: FragmentDetailCourseBinding by dataBinding(FragmentDetailCourseBinding::inflate)

    override val viewModel: DetailCourseViewModel by viewModels()

    override val dataBindingViewModel = BR.detailCourseViewModel

    private lateinit var subscription: Subscription

    private lateinit var recycler: RecyclerView

    private lateinit var adapter: DetailCourseTeacherAdapter

    override fun onViewIsCreated(view: View) {

        DetailCourseReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        recycler = binding.recycler

        recycler.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        observeUiStates()
    }

    private fun observeUiStates() {

        subscription = requireArguments().getSerializable(Config.SUBSCRIPTION_PUT) as Subscription

        setDetailCourse(subscription)

        viewModel.apply {
            lifecycleScopeCreate(activity = requireActivity(), method = {
                state.collect { state ->
                    DetailCourseReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = requireActivity(), method = {
                effect.collect { effect ->
                    DetailCourseReducer.selectEffect(effect)
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

    private fun setDetailCourse(data: Subscription) {
        with(binding){

            topBarInclude.title = data.course!!.title
            titleText.text = data.course!!.title
            descriptionText.text = data.course!!.descriptionShort!!.html()
            timeText.text = data.course!!.formatAsynchronousHour()
            modalityText.text = data.course!!.formatModality()
            topicText.text = data.course!!.lessonsCount.toString()

            adapter = DetailCourseTeacherAdapter(teachers = data.group!!.teachers!!)

            recycler.adapter = adapter
        }
    }


    override fun getDataDetailCourse(data: Subscription) {

    }
}