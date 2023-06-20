package com.ead.project.ourivesariarumor.domain.model

import android.os.Parcelable
import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ead.project.ourivesariarumor.data.util.TimeUtil
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity(tableName = "products_table")
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id : Long? = null,
    val name : String,
    val description : String,
    val image : String?,
    val price : Double,
    val quantity : Int,
    val currency : Currency = Currency.EUR,
    val categories : List<Category>,
    val requestedTimes : Int,
    val popularityPoints : Float,
    val publicationDate : Date = TimeUtil.getNow(),
    val refilledDate : Date = TimeUtil.getNow()
) : Parcelable {

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun fromData(data: Map<String, Any>): Product {
            val id = data["id"] as Long?
            val name = data["name"] as String
            val description = data["description"] as String
            val image = data["image"] as String?
            val price = data["price"] as Double
            val quantity = (data["quantity"] as? Long)?.toInt() ?: 0
            val currencyString = data["currency"] as String
            val currency = Currency.values().find { it.symbol == currencyString }
                ?: throw IllegalArgumentException("Invalid currency value: $currencyString")

            val requestedTimes = (data["requestedTimes"] as Long).toInt()

            val popularityPoints = (data["popularityPoints"] as String).toFloat()

            val categories = (data["categories"] as List<Map<String, Any>>).map { categoryData ->
                val categoryId = categoryData["id"] as Long
                val categoryTitle = categoryData["title"] as String
                val categoryDescription = categoryData["description"] as String
                Category(categoryId, categoryTitle, categoryDescription)
            }

            Log.d("testing", "fromData: $categories")

            return Product(
                id, name, description, image,
                price, quantity, currency,categories,
                requestedTimes, popularityPoints
            )
        }
    }
}


