package com.ead.project.ourivesariarumor.domain.use_case.inventory

import android.net.Uri
import com.ead.project.ourivesariarumor.data.service.ProductService
import com.ead.project.ourivesariarumor.domain.model.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddProduct @Inject constructor(private val productService: ProductService) {

    suspend operator fun invoke(product: Product,productPhotoUri: Uri) : Boolean {
        return productService.createProductTable(product,productPhotoUri)
    }
}