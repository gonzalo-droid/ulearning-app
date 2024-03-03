package com.ulearning.ulearning_app.data.mapper

import com.ulearning.ulearning_app.data.remote.entities.response.TopicResponse
import com.ulearning.ulearning_app.domain.model.Topic

interface TopicMapper {

    suspend fun listTopicToDomain(data: List<TopicResponse>): List<Topic>
}
