package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.CoursePercentageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.CourseResponse
import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.domain.model.Course
import com.ulearning.ulearning_app.domain.model.CoursePercentage
import com.ulearning.ulearning_app.domain.model.Subscription


interface CourseMapper {


    suspend fun listSubscriptionToDomain(data: List<SubscriptionResponse>): List<Subscription>

    suspend fun listCourseToDomain(data: List<CourseResponse>): List<Course>

    suspend fun listCoursePercentageToDomain(data: List<CoursePercentageResponse>): List<CoursePercentage>


}