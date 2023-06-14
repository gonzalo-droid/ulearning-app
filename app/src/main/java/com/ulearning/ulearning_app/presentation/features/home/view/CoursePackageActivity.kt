package com.ulearning.ulearning_app.presentation.features.home.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ulearning.ulearning_app.BR
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.dataBinding
import com.ulearning.ulearning_app.core.extensions.html
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putCourse
import com.ulearning.ulearning_app.core.extensions.putInt
import com.ulearning.ulearning_app.core.extensions.putSubscription
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityCoursePackageBinding
import com.ulearning.ulearning_app.domain.model.CoursePackage
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.presentation.base.BaseActivityWithViewModel
import com.ulearning.ulearning_app.presentation.features.courseDetail.DetailCourseActivity
import com.ulearning.ulearning_app.presentation.features.home.adapter.CoursePackageViewPagerAdapter
import com.ulearning.ulearning_app.presentation.features.home.event.CoursePackageEvent
import com.ulearning.ulearning_app.presentation.features.home.reducer.CoursePackageReducer
import com.ulearning.ulearning_app.presentation.features.home.viewModel.CoursePackageViewModel
import com.ulearning.ulearning_app.presentation.features.home.viewState.CoursePackageViewState
import com.ulearning.ulearning_app.presentation.model.design.MessageDesign
import com.ulearning.ulearning_app.presentation.utils.imageLoader.ImageLoaderGlide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoursePackageActivity :
    BaseActivityWithViewModel<ActivityCoursePackageBinding, CoursePackageViewModel>(),
    CoursePackageViewState {

    override val binding: ActivityCoursePackageBinding by dataBinding(
        ActivityCoursePackageBinding::inflate
    )

    override val viewModel: CoursePackageViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val upArrow = resources.getDrawable(R.drawable.ic_arrow_back)

        supportActionBar!!.setHomeAsUpIndicator(upArrow)

        CoursePackageReducer.instance(viewState = this)

        initTabLayout()

        observeUiStates()
    }

    private fun observeUiStates() {

        viewModel.let {
            viewModel.learningPackageId =
                Config.COURSE_PACKAGE_ID_PUT putInt this@CoursePackageActivity

            viewModel.setEvent(CoursePackageEvent.CoursePackageClicked)
        }


        viewModel.apply {
            lifecycleScopeCreate(activity = this@CoursePackageActivity, method = {
                state.collect { state ->
                    CoursePackageReducer.selectState(state)
                }
            })

            lifecycleScopeCreate(activity = this@CoursePackageActivity, method = {
                effect.collect { effect ->
                    CoursePackageReducer.selectEffect(effect)
                }
            })
        }
    }

    override fun messageFailure(failure: Failure) {
        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
    }

    override fun getCoursePackage(course: CoursePackage) {
        with(binding) {

            if (!course.learningPackage?.mainImage?.originalUrl.isNullOrEmpty()) {
                ImageLoaderGlide().loadImage(
                    imageView = imageCourseIv,
                    imagePath = course.learningPackage?.mainImage?.originalUrl!!,
                    requestOptions = RequestOptions.centerCropTransform(),
                    placeHolder = R.drawable.course_test
                )
            } else {
                imageCourseIv.setImageResource(R.drawable.course_test)
            }

            toolbarLayout.title = course.learningPackage?.title
            titleText.text = course.learningPackage?.title

            viewModel.items = course.learningPackage?.items

            viewModel.setEvent(CoursePackageEvent.ListCoursesPackageClicked)


        }
    }

    private fun onItemSelected(model: Subscription) {

        startActivity(Intent(this, DetailCourseActivity::class.java).apply {
            putExtra(Config.COURSE_PUT, model.course)
            putExtra(Config.SUBSCRIPTION_PUT, model)
            putExtra(Config.ROLE, viewModel.typeRole)
        })
    }

    private fun initTabLayout() {
        binding.viewPager.apply {
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = CoursePackageViewPagerAdapter(supportFragmentManager, lifecycle)
        }

        // binding.viewPager.isSaveEnabled = false

        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                COURSES -> tab.text = "Cursos"
                DETAIL -> tab.text = "Descripcion"
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val COURSES = 0
        const val DETAIL = 1
    }
}
