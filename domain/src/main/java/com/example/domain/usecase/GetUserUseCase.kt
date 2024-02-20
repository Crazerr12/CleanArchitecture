package com.example.domain.usecase

import com.example.domain.entity.User
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    configuration: UseCase.Configuration,
    private val userRepository: UserRepository,
) : UseCase<GetUserUseCase.Request, GetUserUseCase.Response>(configuration) {
    override fun process(request: Request): Flow<Response> =
        userRepository.getUser(request.userId).map { Response(it) }

    data class Request(
        val userId: Long,
    ) : UseCase.Request

    data class Response(
        val user: User,
    ) : UseCase.Response
}