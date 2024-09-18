package com.demo.marketplacemobileapp.di

import com.demo.marketplacemobileapp.config.config
import com.demo.marketplacemobileapp.data.local.dao.CartItemDAO
import com.demo.marketplacemobileapp.data.remote.requests.PostAPI
import com.demo.marketplacemobileapp.data.remote.requests.ProductAPI
import com.demo.marketplacemobileapp.data.repository.PostRepositoryImpl
import com.demo.marketplacemobileapp.data.repository.ProductRepositoryImpl
import com.demo.marketplacemobileapp.domain.repository.PostRepository
import com.demo.marketplacemobileapp.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePostAPI(): PostAPI {
        return Retrofit.Builder()
            .baseUrl(config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostAPI::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(postAPI: PostAPI): PostRepository {
        return PostRepositoryImpl(postAPI)
    }

    @Provides
    @Singleton
    fun provideProductAPI(): ProductAPI {
        return Retrofit.Builder()
            .baseUrl(config.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productAPI: ProductAPI): ProductRepository {
        return ProductRepositoryImpl(productAPI)
    }
}