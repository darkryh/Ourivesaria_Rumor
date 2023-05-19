package com.ead.project.ourivesariarumor.presentation.settings.options.add_product_setting_screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.presentation.register.components.OutlineHintTextField
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductSettingScreen(
    modifier: Modifier = Modifier,
    addProductShape : Shape = CircleShape,
    viewModel: AddProductViewModel = hiltViewModel()
) {
    val productPhotoUri = viewModel.productPhotoUri.value
    val productNameTextField = viewModel.productName.value
    val productDescriptionTextField = viewModel.productDescription.value
    val productPriceTextField = viewModel.productPrice.value
    val productQuantityTextField = viewModel.productQuantity.value
    val productCategories = viewModel.productCategories.value

    val focusRequesters = List(5) { FocusRequester() }
    val snackBarHostState = remember { SnackbarHostState() }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? -> viewModel.onEvent(AddProductEvent.SetProductPhotoUri(uri)) }
    )

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event: UiEvent ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message,
                        duration = event.duration
                    )
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant, addProductShape)
                    .clip(addProductShape)
                    .clickable {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                    .padding(all = 32.dp)
            ) {
                if (productPhotoUri == null) {
                    Icon(
                        modifier = Modifier
                            .height(140.dp)
                            .width(140.dp),
                        painter = painterResource(id = R.drawable.ic_add_product_picture),
                        contentDescription = "icon add product picture",
                        tint = MaterialTheme.colorScheme.inverseSurface
                    )
                }
                else {
                    AsyncImage(
                        model = productPhotoUri,
                        contentDescription = null,
                        modifier = Modifier
                            .height(140.dp)
                            .width(140.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            OutlineHintTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequesters[0]),
                text = productNameTextField.text,
                onValueChange = { viewModel.onEvent(AddProductEvent.EnteredProductName(it)) },
                onFocusChange = { viewModel.onEvent(AddProductEvent.ChangeProductNameFocus(it)) },
                hint = productNameTextField.hint,
                isHintContentVisible = productNameTextField.isHintVisible,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesters[1].requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlineHintTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequesters[1]),
                text = productDescriptionTextField.text,
                onValueChange = { viewModel.onEvent(AddProductEvent.EnteredProductDescription(it)) },
                onFocusChange = { viewModel.onEvent(AddProductEvent.ChangeProductDescriptionFocus(it)) },
                hint = productDescriptionTextField.hint,
                isHintContentVisible = productDescriptionTextField.isHintVisible,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesters[2].requestFocus() }
                ),
                singleLine = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlineHintTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequesters[2]),
                text = productPriceTextField.text,
                onValueChange = { viewModel.onEvent(AddProductEvent.EnteredProductPrice(it)) },
                onFocusChange = { viewModel.onEvent(AddProductEvent.ChangeProductPriceFocus(it)) },
                hint = productPriceTextField.hint,
                isHintContentVisible = productPriceTextField.isHintVisible,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesters[3].requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlineHintTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequesters[3]),
                text = productQuantityTextField.text,
                onValueChange = { viewModel.onEvent(AddProductEvent.EnteredProductQuantity(it)) },
                onFocusChange = { viewModel.onEvent(AddProductEvent.ChangeProductQuantityFocus(it)) },
                hint = productQuantityTextField.hint,
                isHintContentVisible = productQuantityTextField.isHintVisible,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusRequesters[4].requestFocus() }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.onEvent(AddProductEvent.AddNewProduct) }) {
                Text(text = "Upload product")
            }
        }
    }
}