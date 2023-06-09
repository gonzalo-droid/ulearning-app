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
import com.ulearning.ulearning_app.databinding.ActivityCoursePacksBinding
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.courses.DetailCourseActivity
import com.ulearning.ulearning_app.presentation.features.home.adapter.CourseSubscriptionAdapter
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.CoursePackReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CoursePackViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewState.CoursePackViewState
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursePackActivity :
    BaseActivityWithViewModel<ActivityCoursePacksBinding, CoursePackViewModel>(),
    CoursePackViewState {

    override val binding: ActivityCoursePacksBinding by dataBinding(
        ActivityCoursePacksBinding::inflate
    )

    override val viewModel: CoursePackViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackViewModel

    private lateinit var courseRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoursePackReducer.instance(viewState = this)

        binding.topBarInclude.btnBack.setOnClickListener {
            finish()
        }

        courseRecycler = binding.courseRecycler

        courseRecycler.layoutManager = LinearLayoutManager(this@CoursePackActivity)

        binding.noDataInclude.root.visibility = View.GONE
        binding.courseRecycler.visibility = View.INVISIBLE
        binding.skeletonInclude.root.visibility = View.VISIBLE

        observeUiStates()
    }

    private fun observeUiStates() {
        viewModel.setEvent(CoursePackEvent.CoursePackClicked)

        viewModel.apply {
            lifecycleScopeCreate(activity = this@CoursePackActivity, method = {
                state.collect { state ->
                    CoursePackReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@CoursePackActivity, method = {
                effect.collect { effect ->
                    CoursePackReducer.selectEffect(effect)
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

    override fun getCourseComplete(courses: List<Subscription>) {
        binding.skeletonInclude.root.visibility = View.INVISIBLE
        if (courses.isNullOrEmpty()) {
            binding.courseRecycler.visibility = View.INVISIBLE
            binding.noDataInclude.root.visibility = View.VISIBLE
        } else {
            binding.noDataInclude.root.visibility = View.GONE
            courseRecycler.adapter = CourseSubscriptionAdapter(courses = courses) { model ->
                onItemSelected(model)
            }
            binding.courseRecycler.visibility = View.VISIBLE
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