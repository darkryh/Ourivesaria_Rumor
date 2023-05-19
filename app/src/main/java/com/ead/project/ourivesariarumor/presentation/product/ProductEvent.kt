package com.ead.project.ourivesariarumor.presentation.product

sealed class ProductEvent {
    object OnQuantityPlus : ProductEvent()
    object OnQuantityMinus : ProductEvent()
}
