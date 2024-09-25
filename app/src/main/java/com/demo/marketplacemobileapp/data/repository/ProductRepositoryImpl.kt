package com.demo.marketplacemobileapp.data.repository

import com.demo.marketplacemobileapp.data.remote.dto.ProductDTO
import com.demo.marketplacemobileapp.data.remote.requests.ProductAPI
import com.demo.marketplacemobileapp.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productAPI: ProductAPI
) : ProductRepository {

    override suspend fun getProductById(id: Long, token: String): ProductDTO {
        return productAPI.getProductById(token, id)
    }

}