package com.babakbelur.studiary.core.domain.model

data class User(
    val accessToken: String? = null,
    val user: List<UserItem> = emptyList(),
    val refreshToken: String? = null
)

data class UserItem(
    val idUser: Int = 0,
    val username: String? = null,
    val password: String? = null,
    val name: String? = null,
    val role: String? = null
)
