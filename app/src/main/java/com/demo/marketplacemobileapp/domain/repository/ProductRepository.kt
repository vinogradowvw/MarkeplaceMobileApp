package com.demo.marketplacemobileapp.domain.repository

import com.demo.marketplacemobileapp.data.remote.dto.ProductDTO

interface ProductRepository {

    suspend fun getProductById(id: Long): ProductDTO

}