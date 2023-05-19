package com.ead.project.ourivesariarumor.presentation.home

import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.domain.model.Category
import com.ead.project.ourivesariarumor.domain.model.Currency
import com.ead.project.ourivesariarumor.domain.model.Product

internal val categories : List<Category> = listOf(
    Category(
        title = "High-end jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open High-end jewelry category"
    ),
    Category(
        title = "Fashion jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open Fashion jewelry category"
    ),
    Category(
        title = "Hand-made jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open Hand-made jewelry category"
    ),
    Category(
        title = "Vintage jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open Vintage jewelry category"
    ),
    Category(
        title = "Silver jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open Silver jewelry category"
    ),
    Category(
        title = "Gold jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open Gold jewelry category"
    ),
    Category(
        title = "Diamond jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open Diamond jewelry category"
    ),
    Category(
        title = "Gemstone jewelry",
        drawableRes = R.drawable.ic_diamond,
        contentDescription = "open Gemstone jewelry category"
    )
)

internal val topProductsItems : List<Product> = listOf(
    Product(
        name = "product 1",
        description = "This is random description",
        image = "https://i.ibb.co/cCxVqp8/jewlery-removebg-preview.png",
        price = 3.99,
        currency = Currency.EUR,
        quantity = -1,
        categories = listOf(
            Pair(0L,"category1"),
            Pair(0L,"category2"),
            Pair(0L,"category3"),
            Pair(0L,"category4"),
            Pair(0L,"category5")
        )
    ),
    Product(
        name = "product 2",
        description = "This is random description",
        image = "https://i.ibb.co/cCxVqp8/jewlery-removebg-preview.png",
        price = 5.99,
        currency = Currency.EUR,
        quantity = -1,
        categories = listOf(
            Pair(0L,"category1"),
            Pair(0L,"category2"),
            Pair(0L,"category3"),
            Pair(0L,"category4"),
            Pair(0L,"category5")
        )
    ),
    Product(
        name = "product 3",
        description = "This is random description",
        image = "https://i.ibb.co/cCxVqp8/jewlery-removebg-preview.png",
        price = 0.99,
        currency = Currency.EUR,
        quantity = -1,
        categories = listOf(
            Pair(0L,"category1"),
            Pair(0L,"category2"),
            Pair(0L,"category3"),
            Pair(0L,"category4"),
            Pair(0L,"category5")
        )
    ),
    Product(
        name = "product 4",
        description = "This is random description",
        image = "https://i.ibb.co/cCxVqp8/jewlery-removebg-preview.png",
        price = 1.99,
        currency = Currency.EUR,
        quantity = -1,
        categories = listOf(
            Pair(0L,"category1"),
            Pair(0L,"category2"),
            Pair(0L,"category3"),
            Pair(0L,"category4"),
            Pair(0L,"category5")
        )
    ),
    Product(
        name = "product 5",
        description = "This is random description",
        image = "https://i.ibb.co/cCxVqp8/jewlery-removebg-preview.png",
        price = 7.99,
        currency = Currency.EUR,
        quantity = -1,
        categories = listOf(
            Pair(0L,"category1"),
            Pair(0L,"category2"),
            Pair(0L,"category3"),
            Pair(0L,"category4"),
            Pair(0L,"category5")
        )
    )
)