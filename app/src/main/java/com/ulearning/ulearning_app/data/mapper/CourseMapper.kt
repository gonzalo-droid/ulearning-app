package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TeacherResponse
import com.ulearning.ulearning_app.domain.model.Subscription
import com.ulearning.ulearning_app.domain.model.Teacher


interface CourseMapper {


    suspend fun listSubscriptionToDomain(data: List<SubscriptionResponse>): List<Subscription>


}