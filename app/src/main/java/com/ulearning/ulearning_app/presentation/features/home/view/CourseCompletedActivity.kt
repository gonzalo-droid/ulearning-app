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
import com.ulearning.ulearning_app.databinding.ActivityCourseCompletedBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseActivity
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseCompleteViewState
import com.ulearning.ulearning_app.presentation.features.home.event.CourseCompletedEvent
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.reducer.CourseCompletedReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CourseCompletedViewModel
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseCompletedActivity :
    BaseActivityWithViewModel<ActivityCourseCompletedBinding, CourseCompletedViewModel>(),
    CourseCompleteViewState {

    override val binding: ActivityCourseCompletedBinding by dataBinding(
        ActivityCourseCompletedBinding::inflate
    )

    override val viewModel: CourseCompletedViewModel by viewModels()

    override val dataBindingViewModel = BR.courseCompletedViewModel

    private lateinit var courseRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CourseCompletedReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            finish()
        }

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(this@CourseCompletedActivity)

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(CourseCompletedEvent.CourseCompleteClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@CourseCompletedActivity, method = {
                state.collect { state ->
                    CourseCompletedReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@CourseCompletedActivity, method = {
                effect.collect { effect ->
                    CourseCompletedReducer.selectEffect(effect)
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

    override fun getCourseComplete(courses: List<Subscription>) {
        closeLoadingDialog()
        courseRecycler.adapter = CourseSubscriptionAdapter(courses = courses) { model ->
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