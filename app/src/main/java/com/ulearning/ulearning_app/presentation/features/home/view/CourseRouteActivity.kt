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
import com.ulearning.ulearning_app.databinding.ActivityCourseRouteBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.home.adapter.CoursePackageSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.event.CourseRouteEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.CourseRouteReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CourseRouteViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewState.CourseRouteViewState
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CourseRouteActivity :
    BaseActivityWithViewModel<ActivityCourseRouteBinding, CourseRouteViewModel>(),
    CourseRouteViewState {

    override val binding: ActivityCourseRouteBinding by dataBinding(
        ActivityCourseRouteBinding::inflate
    )

    override val viewModel: CourseRouteViewModel by viewModels()

    override val dataBindingViewModel = BR.courseRouteViewModel

    private lateinit var courseRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CourseRouteReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            finish()
        }

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(this@CourseRouteActivity)

        binding.noDataInclude.root.visibility = View.GONE
        binding.courseRecycler.visibility = View.INVISIBLE
        binding.skeletonInclude.root.visibility = View.VISIBLE

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(CourseRouteEvent.CourseRouteClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@CourseRouteActivity, method = {
                state.collect { state ->
                    CourseRouteReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@CourseRouteActivity, method = {
                effect.collect { effect ->
                    CourseRouteReducer.selectEffect(effect)
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

    override fun getCourseRoute(courses: List<Subscription>) {
        binding.skeletonInclude.root.visibility = View.INVISIBLE
        if (courses.isNullOrEmpty()) {
            binding.courseRecycler.visibility = View.INVISIBLE
            binding.noDataInclude.root.visibility = View.VISIBLE
        } else {
            binding.noDataInclude.root.visibility = View.GONE
            courseRecycler.adapter = CoursePackageSubscriptionAdapter(
                courses = courses, type = "route"
            ) { model ->
                onItemSelected(model)
            }
            binding.courseRecycler.visibility = View.VISIBLE
        }
    }

    private fun onItemSelected(model: Subscription) {

        startActivity(
            Intent(this, CoursePackageActivity::class.java).apply {
                putExtra(Config.COURSE_PACKAGE_ID_PUT, model.learningPackage?.id)
            }
        )
    }
}
