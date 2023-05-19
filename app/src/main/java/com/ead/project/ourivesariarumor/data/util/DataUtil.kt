package com.ead.project.ourivesariarumor.data.util

import com.ead.project.ourivesariarumor.domain.model.Cart
import com.ead.project.ourivesariarumor.domain.model.Order
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.domain.model.users.UserSignIn
import java.time.ZoneId


fun hashMap(userSignIn: UserSignIn) : HashMap<String,*> {
    val instant = userSignIn.birthDay.atStartOfDay(ZoneId.systemDefault()).toInstant()
    return hashMapOf(
        "name" to userSignIn.name,
        "lastName" to userSignIn.lastName,
        "birthDay" to instant.toEpochMilli(),
        "email" to userSignIn.email,
        "address" to userSignIn.address,
        "address_line1" to userSignIn.addressLine1,
        "address_line2" to userSignIn.addressLine2,
        "phone" to userSignIn.phone
    )
}

fun hashMap(product: Product) : HashMap<String,*> {
    return hashMapOf(
        "name" to product.name,
        "description" to product.description,
        "image" to product.image,
        "price" to product.price,
        "quantity" to product.quantity,
        "currency" to product.currency.symbol
    )
}

fun hashMap(order: Order) : HashMap<String,*> {
    return hashMapOf(
        "userId" to order.userId,
        "productId" to order.productId,
        "quantity" to order.quantity,
        "totalPrice" to order.totalPrice,
        "status" to order.status
    )
}

fun hashMap(cart: Cart) : HashMap<String,*> {
    return hashMapOf(
        "userId" to cart.userId,
        "productId" to cart.productId,
        "quantity" to cart.quantity,
        "totalPrice" to cart.totalPrice
    )
}