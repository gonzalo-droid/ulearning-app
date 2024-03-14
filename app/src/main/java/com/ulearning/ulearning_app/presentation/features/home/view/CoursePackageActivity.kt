package com.ulearning.ulearning_app.presentation.features.home.view

import android.annotation.SuppressLint
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
import com.ulearning.ulearning_app.core.extensions.lifecycleScopeCreate
import com.ulearning.ulearning_app.core.extensions.putInt
import com.ulearning.ulearning_app.core.functional.Failure
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.databinding.ActivityCoursePackageBinding
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.LearningPackage
import com.ulearning.ulearning_app.domain.model.LearningPackageItem
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
        ActivityCoursePackageBinding::inflate,
    )

    override val viewModel: CoursePackageViewModel by viewModels()

    override val dataBindingViewModel = BR.coursePackageViewModel

    private val courseIds: MutableList<Int> = mutableListOf()
    private val courseIdsWithRequired: MutableList<CourseInfo> = mutableListOf()
    lateinit var learningPackage: LearningPackage
    var percentage: Int = 0

    data class CourseInfo(
        val courseId: Int,
        val isRequired: Boolean,
        val selfStudyHour: Int
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = binding.toolbar

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val upArrow = resources.getDrawable(R.drawable.ic_arrow_back)

        supportActionBar!!.setHomeAsUpIndicator(upArrow)

        CoursePackageReducer.instance(viewState = this@CoursePackageActivity)

        binding.percentageText.text = "$percentage %"
        binding.progressBar.progress = percentage.toInt()

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
        closeLoadingDialog()
        val messageDesign: MessageDesign = getUseCaseFailureFromBase(failure)

        showSnackBar(binding.root, getString(messageDesign.idMessage))
    }

    override fun loading() {
        showLoadingDialog()
    }

    override fun getCoursePackage(
        course: Subscription,
        percentages: List<CoursePercentage>?,
    ) {
        closeLoadingDialog()

        viewModel.setCoursePackage(course)
        viewModel.setPercentages(percentages)

        learningPackage = course.learningPackage!!
        course.learningPackage?.items?.forEach {
            courseIds.add(it.courseId!!)
            courseIdsWithRequired.add(
                CourseInfo(
                    courseId = it.courseId,
                    isRequired = it.isRequired,
                    selfStudyHour = it.course?.selfStudyHour ?: 0
                )
            )
        }

        with(binding) {
            if (!course.learningPackage?.mainImage?.originalUrl.isNullOrEmpty()) {
                ImageLoaderGlide().loadImage(
                    imageView = imageCourseIv,
                    imagePath = course.learningPackage?.mainImage?.originalUrl!!,
                    requestOptions = RequestOptions.centerCropTransform(),
                    placeHolder = R.drawable.course_test,
                )
            } else {
                imageCourseIv.setImageResource(R.drawable.course_test)
            }
            imageCourseIv.alpha = 0.5f

            toolbarLayout.title = " "
            titleText.text = course.learningPackage?.title

            viewModel.setSharedData(course.learningPackage?.items!!)

            initTabLayout(course.learningPackage!!, percentages?.let { ArrayList(it) })

            calculatePercentage(percentages)

            binding.percentageText.text = "$percentage %"
            binding.progressBar.progress = percentage.toInt()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculatePercentage(percentages: List<CoursePercentage>?) {
        if (courseIds.isEmpty()) return


        if (learningPackage.id != null) {
            percentages?.let {
                val value = calculateCourseAdvances(
                    courseAdvances = it,
                    courseIdsWithRequired = courseIdsWithRequired
                )
                percentage = if (value > 100) 100 else value
            }
            return
        }

        var percentage = 0
        percentages?.forEach { item ->
            percentage += if (item.percentage.toInt() < 100) item.percentage.toInt() else 100
        }
        percentage /= courseIds.size

    }

    private fun calculateCourseAdvances(
        courseAdvances: List<CoursePercentage>,
        courseIdsWithRequired: MutableList<CourseInfo>
    ): Int {
        var requiredHoursCompleted = 0.0
        var optionalHoursCompleted = 0.0
        var requiredHoursToComplete = 0.0
        val requiredHours = learningPackage.requiredHours ?: 0

        courseIdsWithRequired.forEach { course ->
            if (course.isRequired) {
                requiredHoursToComplete += course.selfStudyHour
            }
        }
        // Recorremos los avances para obtener las horas completadas
        for (advance in courseAdvances) {
            val course = courseIdsWithRequired.find { it.courseId == advance.courseId }
            if (course != null) {
                val factor = advance.percentage.toDouble() / 100
                if (course.isRequired) {
                    requiredHoursCompleted += course.selfStudyHour * factor
                } else {
                    optionalHoursCompleted += course.selfStudyHour * factor
                }
            }
        }

        var percentageAdvance = 0.0
        val totalHoursCompleted = requiredHoursCompleted + optionalHoursCompleted
        var residualHours = requiredHours - requiredHoursCompleted
        if (residualHours < 0) residualHours = 0.0

        percentageAdvance = if (requiredHoursToComplete == requiredHoursCompleted || requiredHours == 0) {
            (totalHoursCompleted / requiredHours) * 100
        } else {
            (requiredHoursCompleted / requiredHours) * 100
        }
        if (percentageAdvance > 100) percentageAdvance = 100.0

        return (percentageAdvance * 100).toInt() / 100
    }

    fun goToDetailCourse(
        model: LearningPackageItem,
        percentage: Int,
    ) {
        if (viewModel.getCoursePackage() != null) {
            startActivity(
                Intent(this, DetailCourseActivity::class.java).apply {
                    putExtra(Config.COURSE_PUT, model.course)
                    putExtra(Config.SUBSCRIPTION_PUT, viewModel.getCoursePackage())
                    putExtra(Config.PERCENTAGE_PUT, percentage)
                },
            )
        }
    }

    private fun initTabLayout(
        learningPackage: LearningPackage,
        percentages: ArrayList<CoursePercentage>?,
    ) {
        binding.viewPager.apply {
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter =
                CoursePackageViewPagerAdapter(
                    supportFragmentManager, lifecycle, learningPackage, percentages,
                )
        }

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager,
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                COURSES -> tab.text = "Cursos"
                DETAIL -> tab.text = "Descripción"
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
