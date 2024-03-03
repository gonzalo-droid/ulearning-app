package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.response.TopicResponse
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.*

interface TopicApi {

    @GET("topics_preview/{${SettingRemote.COURSE_ID}}")
    suspend fun topics(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path(SettingRemote.COURSE_ID) courseId: Int,
    ): Response<BaseResponse<List<TopicResponse>>>
}
