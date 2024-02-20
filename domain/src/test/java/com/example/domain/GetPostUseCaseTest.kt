package com.example.domain

import com.example.domain.entity.Post
import com.example.domain.repository.PostRepository
import com.example.domain.usecase.GetPostUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetPostUseCaseTest {

    private val postRepository = mock<PostRepository>()
    private val useCase = GetPostUseCase(
        mock(),
        postRepository,
    )

    @Test
    fun testProcess() = runTest {
        val post = Post(1L, 1L, "title", "body")
        val request = GetPostUseCase.Request(1L)

        `when`(postRepository.getPost(request.postId)).thenReturn(flowOf(post))

        val response = useCase.process(request).first()

        assertEquals(GetPostUseCase.Response(post), response)
    }
}