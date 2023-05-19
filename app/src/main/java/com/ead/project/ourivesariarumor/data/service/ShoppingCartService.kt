package com.ead.project.ourivesariarumor.data.service

import com.ead.project.ourivesariarumor.data.repository.FirebaseClient
import com.ead.project.ourivesariarumor.data.util.hashMap
import com.ead.project.ourivesariarumor.domain.model.Cart
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingCartService @Inject constructor(
    private val firebase : FirebaseClient
) {

    private val shoppingCartCollection by lazy {
        firebase.database.collection(SHOPPING_CART_PATH)
    }

    suspend fun createShoppingCartTable(cart: Cart) = runCatching {
        shoppingCartCollection
            .document((shoppingCartCollection.get().result.size() + 1).toString())
            .set(hashMap(cart = cart))
            .await()
    }.isSuccess

    companion object {
        const val SHOPPING_CART_PATH = "shopping_carts"
    }
}