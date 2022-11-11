package com.ulearning.ulearning_app.data.mapper


import com.ulearning.ulearning_app.data.remote.entities.response.CoursePercentageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.CourseResponse
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
                    id = it.course!!.id,
                    lessonsCount = it.course?.lessonsCount,
                    modality = it.course?.modality,
                    asynchronousHour = it.course?.asynchronousHour,
                    amount = 0,
                    benefits = null,
                    certificate = null,
                    code = null,
                    currency = null,
                    duration = null,
                    externalId = null,
                    externalLink = null,
                    groups = listOf(),
                    instructions = null,
                    languageId = null,
                    methodology = null,
                    nature = null,
                    origin = null,
                    politicsLink = null,
                    presentationLink = null,
                    ratingAverage = null,
                    ratingCount = null,
                    record = null,
                    selfStudyHour = null,
                    slug = null,
                    studentsCount = null,
                    syllabusLink = null,
                    synchronousHour = null,
                    target = null,
                ),
                user = User(
                    name = it.userResponse?.name,
                ),
                courseId = it.courseId,
                group = Group(
                    id = it.group?.id,
                    name = it.group?.name,
                    teachers = if (!it.group?.teachers.isNullOrEmpty()) listTeacherToDomain(it.group?.teachers) else arrayListOf(),
                ),
                groupId = it.groupId,
                isFinished = it.isFinished
            )
        }
    }

    override suspend fun listCourseToDomain(data: List<CourseResponse>): List<Course> {
        return data.map {
            Course(
                category = if (it.category != null) Category(
                    color = it.category?.color,
                    description = it.category?.description,
                    id = it.category?.id,
                    name = it.category?.name,
                    type = it.category?.type
                ) else null,
                categoryId = it.categoryId,
                descriptionLarge = it.descriptionLarge,
                descriptionShort = it.descriptionShort,
                mainImage = if (it.mainImage != null) MainImage(
                    originalUrl = it.mainImage?.originalUrl,
                    previewUrl = it.mainImage?.previewUrl,
                ) else null,
                title = it.title,
                id = it.id,
                lessonsCount = it.lessonsCount,
                modality = it.modality,
                asynchronousHour = it.asynchronousHour,
                amount = 0,
                benefits = null,
                certificate = null,
                code = null,
                currency = null,
                duration = null,
                externalId = null,
                externalLink = null,
                groups = listOf(),
                instructions = null,
                languageId = null,
                methodology = null,
                nature = null,
                origin = null,
                politicsLink = null,
                presentationLink = null,
                ratingAverage = null,
                ratingCount = null,
                record = null,
                selfStudyHour = null,
                slug = null,
                studentsCount = null,
                syllabusLink = null,
                synchronousHour = null,
                target = null,
            )
        }
    }

    override suspend fun listCoursePercentageToDomain(data: List<CoursePercentageResponse>): List<CoursePercentage> {
        return data.map {
            CoursePercentage(
                percentage = it.percentage,
                courseId = it.courseId
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