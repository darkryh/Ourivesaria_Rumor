package com.ead.project.ourivesariarumor.data.database

import androidx.room.Dao
import androidx.room.Query
import com.ead.project.ourivesariarumor.domain.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("select * from products_table")
    fun getProducts() : Flow<List<Product>>

    @Query("select * from products_table order by popularityPoints desc")
    fun getPopularsProducts() : Flow<List<Product>>

    @Query("select * from products_table order by publicationDate desc")
    fun getNewProducts() : Flow<List<Product>>

    @Query("select * from products_table order by refilledDate desc")
    fun getRefilledProducts() : Flow<List<Product>>
}