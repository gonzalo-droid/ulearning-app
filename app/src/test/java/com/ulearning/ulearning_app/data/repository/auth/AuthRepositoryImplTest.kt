package com.ulearning.ulearning_app.data.repository.auth

import com.ulearning.ulearning_app.data.remote.api.AuthApi
import com.ulearning.ulearning_app.data.remote.entities.request.LoginRequest
import com.ulearning.ulearning_app.data.remote.entities.response.LoginResponse
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthRepositoryImplTest {

    private lateinit var service: AuthApi


    companion object {
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon() {
            retrofit = Retrofit.Builder()
                .baseUrl("https://api.ulearning.com.pe/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup() {
        service = retrofit.create(AuthApi::class.java)
    }


    @Test
    fun `login return success test`() {
        runBlocking {
            val result = service.login(
                LoginRequest(email = "admin@gmail.com", password = "ulearning2022")
            )

            assertThat(result.body()!!.token.isNotEmpty(), `is` (true))

        }
    }

    @Test
    fun loginReturnErrorTest(){
        runBlocking {
            try {
                service.login(
                    LoginRequest(email = "qwr", password = "qrwr")
                )
            } catch (e: Exception) {
                assertThat(e, `is`(false))
            }
        }
    }

}