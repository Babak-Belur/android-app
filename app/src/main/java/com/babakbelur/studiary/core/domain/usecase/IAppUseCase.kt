package com.babakbelur.studiary.core.domain.usecase

interface IAppUseCase {

    suspend fun signIn(username: String, password: String)
}