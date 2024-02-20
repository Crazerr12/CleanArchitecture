package com.example.domain.usecase

import com.example.domain.entity.Interaction
import com.example.domain.repository.InteractionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UpdateInteractionUseCase @Inject constructor(
    configuration: UseCase.Configuration,
    private val interactionRepository: InteractionRepository,
) : UseCase<UpdateInteractionUseCase.Request, UpdateInteractionUseCase.Response>(configuration) {
    override fun process(request: Request): Flow<Response> =
        interactionRepository.saveInteraction(request.interaction).map { Response }

    data class Request(
        val interaction: Interaction,
    ) : UseCase.Request

    object Response : UseCase.Response
}