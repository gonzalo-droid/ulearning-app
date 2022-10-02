package com.ulearning.ulearning_app.domain.model

import com.ulearning.ulearning_app.data.remote.entities.response.FirstMessageResponse
import java.io.Serializable

data class Conversation(
    val canByReply: Boolean? = false,
    val courseId: Int? = 0,
    val firstMessage: FirstMessageResponse? = FirstMessageResponse(),
    val id: Int? = 0,
    val isBroadcast: Boolean? = false,
    val replyToAuthor: Boolean? = false,
    val toSupport: Boolean? = false,
    val uuid: String? = ""
) : Serializable

