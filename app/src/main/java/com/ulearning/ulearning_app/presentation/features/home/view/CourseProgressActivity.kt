package com.ulearning.ulearning_app.presentation.features.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import com.ulearning.ulearning_app.presentation.features.courseDetail.DetailCourseActivity
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.event.CourseProgressEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.CourseProgressReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CourseProgressViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseProgressViewState
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

        binding.noDataInclude.root.visibility = View.GONE
        binding.courseRecycler.visibility = View.INVISIBLE
        binding.skeletonInclude.root.visibility = View.VISIBLE

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
        binding.skeletonInclude.root.visibility = View.VISIBLE
    }

    override fun getCourseRecent(courses: List<Subscription>, percentages: List<CoursePercentage>) {
        binding.skeletonInclude.root.visibility = View.INVISIBLE

        if (courses.isEmpty()) {
            binding.courseRecycler.visibility = View.INVISIBLE
            binding.noDataInclude.root.visibility = View.VISIBLE
        } else {
            binding.noDataInclude.root.visibility = View.GONE
            courseRecycler.adapter =
                CourseSubscriptionAdapter(
                    courses = courses,
                    percentages = percentages
                ) { model, percentage ->
                    onItemSelected(model, percentage)
                }
            binding.courseRecycler.visibility = View.VISIBLE
        }
    }

    private fun onItemSelected(model: Subscription, percentage: Int) {

        startActivity(
            Intent(this, DetailCourseActivity::class.java).apply {
                putExtra(Config.COURSE_PUT, model.course)
                putExtra(Config.SUBSCRIPTION_PUT, model)
                putExtra(Config.ROLE, viewModel.typeRole)
                putExtra(Config.PERCENTAGE_PUT, percentage)
            }
        )
    }
}
