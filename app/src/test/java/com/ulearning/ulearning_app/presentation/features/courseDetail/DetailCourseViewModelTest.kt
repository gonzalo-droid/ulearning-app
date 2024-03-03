package com.ulearning.ulearning_app.presentation.features.courseDetail

import com.ulearning.ulearning_app.presentation.model.entity.User
import org.junit.AfterClass
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test

class DetailCourseViewModelTest {

    @Test
    fun login_verifyUser_returnTrue() {

        val user = User("gonzalo@gmail.com", "123321")

        val result = user.verifyLogin()

        Assert.assertTrue(result.first)
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUp() {
        }

        @JvmStatic
        @AfterClass
        fun tearDown() {
        }
    }
}
