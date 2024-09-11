package com.demo.marketplacemobileapp.data.remote.dto

import java.sql.Timestamp

data class CartDTO(
    val id: Long,
    val timestamp: Timestamp,
    val User: Long,
    val products: Map<Long, Long>
)