package com.ead.project.ourivesariarumor.presentation.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ead.project.ourivesariarumor.domain.model.Product
import com.ead.project.ourivesariarumor.presentation.main.components.RowItems
import com.ead.project.ourivesariarumor.presentation.product.components.ProductDetailsPreview
import com.ead.project.ourivesariarumor.presentation.product.components.ProductPreview
import com.ead.project.ourivesariarumor.presentation.product.model.UiEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    product: Product,
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = hiltViewModel(),
    onFinishAction : () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val quantity = viewModel.quantity.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.inverseSurface
                ),
                navigationIcon = {
                    IconButton(onClick = onFinishAction) {
                        Icon(
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp),
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "back icon"
                        )
                    }
                },
                title = {return@TopAppBar},
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp),
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "account icon"
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                }
            )
        }
    ) {
        Column(
            modifier = modifier
                .padding(it)
        ) {
            ProductPreview(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(12f),
                product = product,
                quantity = quantity
            )
            Spacer(modifier = Modifier.height(16.dp))
            RowItems(
                modifier = Modifier
                    .height(100.dp),
                items = product.categories,
                withItem = 100.dp,
                heightItem = 84.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            ProductDetailsPreview(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(8f),
                product = product,
                quantity = quantity,
                onMinusAction = { viewModel.onEvent(ProductEvent.OnQuantityMinus) },
                onPlusAction = { viewModel.onEvent(ProductEvent.OnQuantityPlus) }
            )
        }
    }

}