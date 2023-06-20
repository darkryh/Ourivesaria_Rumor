package com.ead.project.ourivesariarumor.data.repository

import com.ead.project.ourivesariarumor.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getProducts() : Flow<List<Product>>

    fun getPopularProducts() :  Flow<List<Product>>

    fun getNewProducts() :  Flow<List<Product>>

    fun getRefilledProducts() :  Flow<List<Product>>

}