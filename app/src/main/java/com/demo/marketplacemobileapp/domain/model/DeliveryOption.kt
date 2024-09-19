package com.demo.marketplacemobileapp.domain.model

enum class DeliveryOptions(
    val title: String,
    val deliveryDuration: String,
    val price: Float
) {
    Standard(
        title = "Standard delivery",
        deliveryDuration = "5-8 business days",
        price = 0.00f
    ),
    Express(
        title = "Express delivery",
        deliveryDuration = "2-4 business days",
        price = 5.99f
    )
}