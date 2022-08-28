package com.ulearning.ulearning_app.data.mapper


import com.ulearning.ulearning_app.data.remote.entities.response.SubscriptionResponse
import com.ulearning.ulearning_app.domain.model.*
import javax.inject.Singleton

@Singleton
class CourseMapperImpl : CourseMapper {

    override suspend fun listSubscriptionToDomain(data: List<SubscriptionResponse>): List<Subscription> {
        return data.map {
            Subscription(
                course = Course(
                    category = Category(
                        card_image = CardImage(
                            originalUrl = it.course.category.cardImage.originalUrl,
                            previewUrl = it.course.category.cardImage.previewUrl,
                        ),
                        color = it.course.category.color,
                        description = it.course.category.description,
                        id = it.course.category.id,
                        name = it.course.category.name,
                        type = it.course.category.type
                    ),
                    categoryId = it.course.categoryId,
                    descriptionLarge = it.course.descriptionLarge,
                    descriptionShort = it.course.descriptionShort,
                    mainImage = MainImage(
                        originalUrl = it.course.mainImage.originalUrl,
                        previewUrl = it.course.mainImage.previewUrl,
                    ),
                    title = it.course.title,
                    id = it.course.id,
                ),
                course_id = it.courseId,
                group = Group(
                    id = it.group.id,
                    name = it.group.name,
                ),
                group_id = it.groupId,
            )
        }
    }


}