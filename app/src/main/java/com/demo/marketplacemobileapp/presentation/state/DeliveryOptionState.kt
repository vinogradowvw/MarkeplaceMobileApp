package com.demo.marketplacemobileapp.presentation.state

import com.demo.marketplacemobileapp.domain.model.DeliveryOptions

data class DeliveryOptionState(
    val delivery: DeliveryOptions = DeliveryOptions.Standard
)
