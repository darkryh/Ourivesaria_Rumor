package com.ead.project.ourivesariarumor.domain.use_case

import com.ead.project.ourivesariarumor.domain.use_case.inventory.AddProduct
import com.ead.project.ourivesariarumor.domain.use_case.inventory.GetProducts

data class InventoryUseCase(
    val addProduct: AddProduct,
    val getProducts: GetProducts
)
