package com.ead.project.ourivesariarumor.data.repository

import com.ead.project.ourivesariarumor.data.database.CartDao
import com.ead.project.ourivesariarumor.data.database.OrderDao
import com.ead.project.ourivesariarumor.data.database.ProductDao
import com.ead.project.ourivesariarumor.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val orderDao: OrderDao,
    private val cartDao: CartDao
) : AppRepository {

    override fun getProducts(): Flow<List<Product>> {
        return productDao.getProducts()
    }

    override fun getPopularProducts(): Flow<List<Product>> {
        return productDao.getPopularsProducts()
    }

    override fun getNewProducts(): Flow<List<Product>> {
        return productDao.getNewProducts()
    }

    override fun getRefilledProducts(): Flow<List<Product>> {
        return productDao.getRefilledProducts()
    }


}