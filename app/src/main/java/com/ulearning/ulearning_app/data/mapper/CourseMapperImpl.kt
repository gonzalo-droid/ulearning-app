package com.ulearning.ulearning_app.data.mapper


import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TeacherResponse
import com.ulearning.ulearning_app.domain.model.*
import javax.inject.Singleton

@Singleton
class CourseMapperImpl : CourseMapper {

    override suspend fun listSubscriptionToDomain(data: List<SubscriptionResponse>): List<Subscription> {
        return data.map {
            Subscription(
                course = Course(
                    category = if (it.course?.category != null) Category(
                        color = it.course?.category?.color,
                        description = it.course?.category?.description,
                        id = it.course?.category?.id,
                        name = it.course?.category?.name,
                        type = it.course?.category?.type
                    ) else null,
                    categoryId = it.course?.categoryId,
                    descriptionLarge = it.course?.descriptionLarge,
                    descriptionShort = it.course?.descriptionShort,
                    mainImage = if (it.course?.mainImage != null) MainImage(
                        originalUrl = it.course?.mainImage?.originalUrl,
                        previewUrl = it.course?.mainImage?.previewUrl,
                    ) else null,
                    title = it.course?.title,
                    id = it.course?.id,
                    lessonsCount = it.course?.lessonsCount,
                    modality = it.course?.modality,
                    asynchronousHour = it.course?.asynchronousHour,
                ),
                user = User(
                    name = it.userResponse?.name,
                ),
                courseId = it.courseId,
                group = Group(
                    id = it.group?.id,
                    name = it.group?.name,
                    teachers = if(!it.group?.teachers.isNullOrEmpty()) listTeacherToDomain(it.group?.teachers) else arrayListOf(),
                ),
                groupId = it.groupId,
            )
        }
    }

    private fun listTeacherToDomain(data: List<TeacherResponse>?): List<Teacher>? {
        return data?.map {
            Teacher(
                //avatar = it.avatar,
                firstName = it.firstName,
                id = it.id,
                lastName = it.lastName,
                subtype = it.subtype,
                type = it.type
            )
        }
    }

}