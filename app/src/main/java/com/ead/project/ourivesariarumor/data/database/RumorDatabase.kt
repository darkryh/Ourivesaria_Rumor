package com.ead.project.ourivesariarumor.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ead.project.ourivesariarumor.data.util.Converters
import com.ead.project.ourivesariarumor.domain.model.Cart
import com.ead.project.ourivesariarumor.domain.model.Order
import com.ead.project.ourivesariarumor.domain.model.Product

@Database(
    entities = [Product::class,Order::class,Cart::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [Converters::class])
abstract class RumorDatabase : RoomDatabase() {

    abstract fun cartDao() : CartDao

    abstract fun orderDao() : OrderDao

    abstract fun productDao() : ProductDao


    companion object {
        const val DATABASE = "Rumor_Database"
    }
}