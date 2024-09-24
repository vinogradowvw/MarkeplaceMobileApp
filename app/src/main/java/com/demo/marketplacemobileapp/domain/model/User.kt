package com.demo.marketplacemobileapp.domain.model

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val likes: List<Long>,
    val orders: List<Long>,
    val posts: List<Long>,
    val subscribers: List<Long> = emptyList(),
    val subscriptions: List<Long> = emptyList(),
    val cart: Long?,
    val roles: List<Long> = emptyList(),
    val reviews: List<Long> = emptyList()
)
