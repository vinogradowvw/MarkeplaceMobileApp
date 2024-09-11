package com.demo.marketplacemobileapp.domain.model

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val likes: List<Long>,
    val orders: List<Long>,
    val posts: List<Long>,
    val subscribers: List<User>,
    val subscriptions: List<User>,
    val cart: Cart
)
