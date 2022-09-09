package com.ulearning.ulearning_app.domain.useCase.topic

import com.ulearning.ulearning_app.domain.model.Topic
import com.ulearning.ulearning_app.domain.repository.topic.TopicRepository
import com.ulearning.ulearning_app.domain.useCase.BaseUseCase
import javax.inject.Inject

class GetTopicUseCase
@Inject constructor(private val topicRepository: TopicRepository) :
    BaseUseCase<List<Topic>, GetTopicUseCase.Params>() {

    override suspend fun run(params: Params) = topicRepository.getTopics(params.courseId)

    data class Params(val courseId: Int)
}