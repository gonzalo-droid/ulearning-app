package com.ulearning.ulearning_app.presentation.features.auth

import com.ulearning.ulearning_app.domain.repository.auth.AuthRepository
import com.ulearning.ulearning_app.domain.useCase.auth.DoLoginUseCase
import com.ulearning.ulearning_app.presentation.model.entity.User
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

class LoginViewModelTest {

    private lateinit var doLoginUseCase: DoLoginUseCase;
    private lateinit var repository: AuthRepository

    @BeforeClass
    fun setUp() {

        doLoginUseCase = DoLoginUseCase(repository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun login_verifyUser_returnTrue() {

        val user = User("gonzalo@gmail.com", "123321")

        val result = user.verifyLogin()

        assertTrue(result.first)

    }
}