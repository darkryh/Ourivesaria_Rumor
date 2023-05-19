package com.ead.project.ourivesariarumor.data.service

import com.ead.project.ourivesariarumor.data.repository.FirebaseClient
import com.ead.project.ourivesariarumor.data.util.hashMap
import com.ead.project.ourivesariarumor.domain.model.Order
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderService @Inject constructor(
    private val firebase : FirebaseClient
) {

    private val ordersCollection by lazy {
        firebase.database.collection(ORDERS_PATH)
    }

    suspend fun createOrdersTable(order: Order) = runCatching {
        ordersCollection
            .document((ordersCollection.get().result.size() + 1).toString())
            .set(hashMap(order = order))
            .await()
    }.isSuccess

    companion object {
        const val ORDERS_PATH = "orders"
    }
}