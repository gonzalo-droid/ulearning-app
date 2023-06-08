package com.ulearning.ulearning_app.presentation.features.home.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityCourseProgressBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseActivity
import com.ulearning.ulearning_app.presentation.features.home.event.CourseProgressEvent
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseProgressViewState
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.reducer.CourseProgressReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CourseProgressViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CourseProgressActivity :
    BaseActivityWithViewModel<ActivityCourseProgressBinding, CourseProgressViewModel>(),
    CourseProgressViewState {

    override val binding: ActivityCourseProgressBinding by dataBinding(
        ActivityCourseProgressBinding::inflate
    )

    override val viewModel: CourseProgressViewModel by viewModels()

    override val dataBindingViewModel = BR.courseProgressViewModel

    private lateinit var courseRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CourseProgressReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            finish()
        }

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(this@CourseProgressActivity)

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(CourseProgressEvent.CourseRecentClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@CourseProgressActivity, method = {
                state.collect { state ->
                    CourseProgressReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@CourseProgressActivity, method = {
                effect.collect { effect ->
                    CourseProgressReducer.selectEffect(effect)
                }
            })
        }

    }

    override fun messageFailure(failure: Failure) {
        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun getCourseRecent(courses: List<Subscription>, percentages: List<CoursePercentage>) {
        closeLoadingDialog()

        courseRecycler.adapter =
            CourseSubscriptionAdapter(courses = courses, percentages = percentages) { model ->
                onItemSelected(model)
            }
    }

    private fun onItemSelected(model: Subscription) {

        startActivity(Intent(this, DetailCourseActivity::class.java).apply {
            putExtra(Config.COURSE_PUT, model.course)
            putExtra(Config.SUBSCRIPTION_PUT, model)
            putExtra(Config.ROLE, viewModel.typeRole)
        })

    }

}