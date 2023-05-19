package com.ead.project.ourivesariarumor.presentation.product

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ead.project.ourivesariarumor.app.data.util.system.parcelable
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.presentation.theme.OurivesariaRumorProductTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : ComponentActivity() {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()
        setContent {
            OurivesariaRumorProductTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                        product = product,
                        onFinishAction = { finish() }
                    )
                }
            }
        }
    }

    private fun getExtras() {
        intent.extras?.apply {
            product = parcelable<Product>(PRODUCT_PREVIEW)?:return@apply
        }
    }

    companion object {
        const val PRODUCT_PREVIEW = "product preview"
    }
}