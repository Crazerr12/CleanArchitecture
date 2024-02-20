package com.example.domain

import com.example.domain.entity.User
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.GetUserUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetUserUseCaseTest {

    private val userRepository = mock<UserRepository>()
    private val useCase = GetUserUseCase(
        mock(),
        userRepository
    )

    @Test
    fun testProcess() = runTest {
        val user1 = User(1L, "name1", "username1", "email1")
        val request = GetUserUseCase.Request(1)
        `when`(userRepository.getUser(request.userId)).thenReturn(
            flowOf(user1)
        )
        val response = useCase.process(request).first()

        assertEquals(GetUserUseCase.Response(user1), response)
    }
}