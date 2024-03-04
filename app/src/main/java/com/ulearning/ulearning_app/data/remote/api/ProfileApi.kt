package com.ulearning.ulearning_app.data.remote.api

import com.ulearning.ulearning_app.data.remote.entities.BaseResponse
import com.ulearning.ulearning_app.data.remote.entities.response.*
import com.ulearning.ulearning_app.data.remote.utils.SettingRemote
import retrofit2.Response
import retrofit2.http.*

interface ProfileApi {
    @GET("payments")
    suspend fun payments(
        @Header(SettingRemote.AUTHORIZATION) token: String,
        @Query("per_page") perPage: Int = 50,
        @Query("page") page: Int,
    ): Response<BaseResponse<List<PaymentResponse>>>
}
