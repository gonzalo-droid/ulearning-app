package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SendConversationRequest
import com.ulearning.ulearning_app.data.remote.entities.request.SendMessageRequest
import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import com.ulearning.ulearning_app.data.remote.entities.response.MessageResponse
import com.ulearning.ulearning_app.data.remote.entities.response.UserResponse
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.*


interface ConversationApi {

    @GET("conversations")
    suspend fun conversations(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("course_id") courseId: Int,
    ): Response<BaseResponse<List<ConversationResponse>>>


    @GET("messages/{uuid}")
    suspend fun messages(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Path("uuid") uuid: String,
    ): Response<BaseResponse<List<MessageResponse>>>

    @POST("messages")
    suspend fun sendMessage(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Body body: SendMessageRequest,
    ): Response<BaseResponse<MessageResponse>>

    @POST("messages")
    suspend fun sendConversation(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Body body: SendConversationRequest,
    ): Response<BaseResponse<ConversationResponse>>

    @GET("users")
    suspend fun userByCourse(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("name") name: String,
        @Query("course_id") courseId: Int,
        @Query("without_pagination") withoutPagination: Boolean,
    ): Response<BaseResponse<List<UserResponse>>>
}
