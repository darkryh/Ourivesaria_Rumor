package com.ead.project.ourivesariarumor.domain.use_case.inventory

import com.ead.project.ourivesariarumor.data.service.ProductService
import com.ead.project.ourivesariarumor.domain.model.Product
import javax.inject.Inject

class GetProducts @Inject constructor(private val productService: ProductService) {

    suspend operator fun invoke() : List<Product> {
        return productService.getProductsTable()
    }
}