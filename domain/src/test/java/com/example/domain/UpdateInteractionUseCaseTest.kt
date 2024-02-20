package com.example.domain

import com.example.domain.entity.Interaction
import com.example.domain.repository.InteractionRepository
import com.example.domain.usecase.UpdateInteractionUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class UpdateInteractionUseCaseTest {

    private val interactionRepository = mock<InteractionRepository>()
    private val useCase = UpdateInteractionUseCase(
        mock(),
        interactionRepository
    )

    @Test
    fun testProcess() = runTest {
        val interaction = Interaction(10)
        val request = UpdateInteractionUseCase.Request(interaction)
        `when`(interactionRepository.saveInteraction(interaction)).thenReturn(flowOf(interaction))
        val response = useCase.process(request).first()
        assertEquals(UpdateInteractionUseCase.Response, response)
    }
}