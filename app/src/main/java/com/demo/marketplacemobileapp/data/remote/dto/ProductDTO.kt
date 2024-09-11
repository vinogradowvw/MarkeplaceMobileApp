package com.demo.marketplacemobileapp.data.remote.dto

import com.demo.marketplacemobileapp.domain.model.Product
import com.demo.marketplacemobileapp.domain.repository.ProductRepository

data class ProductDTO(
    val id: Long,
    val price: Float,
    val description: String,
    val name: String,
    val post: Long,
    val orders: List<Long>,
    val carts: List<Long>
)

fun ProductDTO.toEntity(): Product {
    return Product(
        id = id,
        price = price,
        name = name
    )
}