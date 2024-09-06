package com.demo.marketplacemobileapp.dataClasses

data class UserDTO(
    val id: Long,
    val username: String,
    val email: String,
    val likes: List<Long>,
    val orders: List<Long>,
    val posts: List<Long>,
    val subscribers: List<Long>,
    val subscriptions: List<Long>,
    val cart: Long
)
