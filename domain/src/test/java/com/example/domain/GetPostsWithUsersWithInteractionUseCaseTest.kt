package com.example.domain

import com.example.domain.entity.Interaction
import com.example.domain.entity.Post
import com.example.domain.entity.PostWithUser
import com.example.domain.entity.User
import com.example.domain.repository.InteractionRepository
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import com.example.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetPostsWithUsersWithInteractionUseCaseTest {

    private val postRepository = mock<PostRepository>()
    private val userRepository = mock<UserRepository>()
    private val interactionRepository = mock<InteractionRepository>()
    private val useCase = GetPostsWithUsersWithInteractionUseCase(
        mock(),
        postRepository,
        userRepository,
        interactionRepository,
    )

    @Test
    fun testProcess() = runTest {
        val user1 = User(1L, "name1", "username1", "email1")
        val user2 = User(2L, "name2", "username2", "email2")
        val post1 = Post(1L, user1.id, "title1", "body1")
        val post2 = Post(2L, user1.id, "title2", "body2")
        val post3 = Post(3L, user2.id, "title3", "body3")
        val post4 = Post(4L, user2.id, "title4", "body4")
        val interaction = Interaction(10)

        `when`(userRepository.getUsers()).thenReturn(flowOf(listOf(user1, user2)))
        `when`(postRepository.getPosts()).thenReturn(flowOf(listOf(post1, post2, post3, post4)))
        `when`(interactionRepository.getInteraction()).thenReturn(flowOf(interaction))

        val response = useCase.process(GetPostsWithUsersWithInteractionUseCase.Request).first()

        assertEquals(
            GetPostsWithUsersWithInteractionUseCase.Response(
                listOf(
                    PostWithUser(post1, user1),
                    PostWithUser(post2, user1),
                    PostWithUser(post3, user2),
                    PostWithUser(post4, user2),
                ), interaction
            ), response
        )
    }
}