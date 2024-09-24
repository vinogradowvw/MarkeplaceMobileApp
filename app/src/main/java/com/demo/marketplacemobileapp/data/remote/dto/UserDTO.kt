package com.demo.marketplacemobileapp.data.remote.dto

import com.demo.marketplacemobileapp.domain.model.User

data class UserDTO(
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val likes: List<Long>,
    val orders: List<Long>,
    val posts: List<Long>,
    val subscribers: List<Long> = emptyList(),
    val subscriptions: List<Long> = emptyList(),
    val cart: Long? = null,
    val roles: List<Long> = emptyList(),
    val reviews: List<Long> = emptyList()
)

fun UserDTO.toEntity(): User {
    return User(
        id = id,
        username = username,
        password = password,
        email = email,
        likes = likes,
        orders = orders,
        posts = posts,
        subscribers = subscribers,
        subscriptions = subscriptions,
        cart = cart,
        roles = roles,
        reviews = reviews
    )
}