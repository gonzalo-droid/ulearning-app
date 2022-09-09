package com.ulearning.ulearning_app.data.mapper


import com.ulearning.ulearning_app.data.remote.entities.response.TopicResponse
import com.ulearning.ulearning_app.domain.model.Topic
import javax.inject.Singleton

@Singleton
class TopicMapperImpl : TopicMapper {

    override suspend fun listTopicToDomain(data: List<TopicResponse>): List<Topic> {
        return data.map {
            Topic(
                children = if(!it.children.isNullOrEmpty()) listTopicToDomain(it.children) else arrayListOf(),
                courseId = it.courseId,
                id = it.id,
                order = it.order,
                parentId = it.parentId,
                title = it.title,
                type = it.type,
            )
        }
    }
}