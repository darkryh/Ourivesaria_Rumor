package com.ead.project.ourivesariarumor.presentation.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.presentation.authentication.component.IconState
import com.ead.project.ourivesariarumor.presentation.register.components.DateField
import com.ead.project.ourivesariarumor.presentation.register.components.OutlineHintTextField
import com.ead.project.ourivesariarumor.presentation.register.model.RegisterState
import com.ead.project.ourivesariarumor.presentation.register.model.UiEvent
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    goToLoginAction: () -> Unit,
    goToVerifyAction: () -> Unit
) {

    val emailTextField = viewModel.email.value
    val passwordTextField = viewModel.password.value
    val nameTextField = viewModel.name.value
    val lastNameTextField = viewModel.lastName.value
    val birthDay = viewModel.birthDay.value
    val phoneTextField = viewModel.phone.value
    val cityTextField = viewModel.city.value
    val addressTextField = viewModel.address.value
    val addressLine1TextField = viewModel.addressLine1.value
    val addressLine2TextField = viewModel.addressLine2.value

    val snackBarHostState = remember { SnackbarHostState() }
    val calendarState = rememberSheetState()

    val emailState = viewModel.emailState.value
    var passwordHide by remember { mutableStateOf(true) }

    val focusRequesters = List(10) { FocusRequester() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar ->
                    snackBarHostState.showSnackbar(event.message)
            }
        }
    }

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date { date ->
            viewModel.onEvent(RegisterEvent.EnteredAge(date))
        },
        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true
        )
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) } ,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = goToLoginAction) {
                        Icon(
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp),
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .padding(
                        vertical = 16.dp,
                        horizontal = 32.dp
                    ),
                onClick = {
                    viewModel.onEvent(RegisterEvent.RegisterUser(goToVerifyAction)) }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "SIGN UP",
                    textAlign = TextAlign.Center
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    vertical = 16.dp,
                    horizontal = 32.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                OutlineHintTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequesters[0]),
                    text = nameTextField.text,
                    onValueChange = { viewModel.onEvent(RegisterEvent.EnteredName(it)) },
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeNameFocus(it)) },
                    hint = nameTextField.hint,
                    isHintContentVisible = nameTextField.isHintVisible,
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
                    text = lastNameTextField.text,
                    onValueChange = { viewModel.onEvent(RegisterEvent.EnteredLastName(it)) },
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeLastNameFocus(it)) },
                    hint = lastNameTextField.hint,
                    isHintContentVisible = lastNameTextField.isHintVisible,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequesters[2].requestFocus() }
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlineHintTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequesters[2]),
                    text = phoneTextField.text,
                    onValueChange = { viewModel.onEvent(RegisterEvent.EnteredPhone(it)) },
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangePhoneFocus(it)) },
                    hint = phoneTextField.hint,
                    isHintContentVisible = phoneTextField.isHintVisible,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
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
                    text = cityTextField.text,
                    onValueChange = { viewModel.onEvent(RegisterEvent.EnteredCity(it)) },
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeCityFocus(it)) },
                    hint = cityTextField.hint,
                    isHintContentVisible = cityTextField.isHintVisible,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequesters[4].requestFocus() }
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlineHintTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequesters[4]),
                    text = addressTextField.text,
                    onValueChange = { viewModel.onEvent(RegisterEvent.EnteredAddress(it)) },
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeAddressFocus(it)) },
                    hint = addressTextField.hint,
                    isHintContentVisible = addressTextField.isHintVisible,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequesters[5].requestFocus() }
                    )
                )
                AnimatedVisibility(visible = !addressTextField.isHintVisible) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlineHintTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequesters[5]),
                            text = addressLine1TextField.text,
                            onValueChange = { viewModel.onEvent(RegisterEvent.EnteredAddressLine1(it)) },
                            onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeAddressLine1Focus(it)) },
                            hint = addressLine1TextField.hint,
                            isHintContentVisible = addressLine1TextField.isHintVisible,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusRequesters[6].requestFocus() }
                            )
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlineHintTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(focusRequesters[6]),
                            text = addressLine2TextField.text,
                            onValueChange = { viewModel.onEvent(RegisterEvent.EnteredAddressLine2(it)) },
                            onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeAddressLine2Focus(it)) },
                            hint = addressLine2TextField.hint,
                            isHintContentVisible = addressLine2TextField.isHintVisible,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = { focusRequesters[7].requestFocus() }
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlineHintTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequesters[7]),
                    text = emailTextField.text,
                    onValueChange = { viewModel.onEvent(RegisterEvent.EnteredEmail(it)) },
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeEmailFocus(it)) },
                    hint = emailTextField.hint,
                    isHintContentVisible = emailTextField.isHintVisible,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusRequesters[8].requestFocus() }
                    ),
                    trailingIcon = {
                        IconState(
                            modifier = Modifier
                                .height(24.dp)
                                .width(24.dp),
                            defaultIcon = Icons.Default.AccountBox,
                            icon =
                            when(emailState){
                                RegisterState.EmailAvailable -> { Icons.Default.Check }
                                RegisterState.EmailNotAvailable -> { Icons.Default.Close }
                                else -> { null}
                            },
                            isLoading = emailState == RegisterState.EmailRequest,
                            tint =
                            when(emailState){
                                RegisterState.EmailAvailable -> { Color.Green }
                                RegisterState.EmailNotAvailable -> { Color.Red }
                                else -> { LocalContentColor.current }
                            }
                        )
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlineHintTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequesters[8]),
                    text = passwordTextField.text,
                    onValueChange = { viewModel.onEvent(RegisterEvent.EnteredPassword(it)) },
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangePasswordFocus(it)) },
                    hint = passwordTextField.hint,
                    isHintContentVisible = passwordTextField.isHintVisible,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusRequesters[9]
                            calendarState.show()
                        }
                    ),
                    visualTransformation =
                    if (passwordHide) PasswordVisualTransformation()
                    else VisualTransformation.None,
                    trailingIcon = {
                        IconButton(onClick = { passwordHide = !passwordHide }) {
                            Icon(
                                painter =
                                painterResource(
                                    id = if (passwordHide) R.drawable.ic_password_close
                                    else R.drawable.ic_password_open
                                ),
                                contentDescription = null)
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                DateField(
                    hint = "Select birthday date.",
                    date = birthDay,
                    onFocusChange = { viewModel.onEvent(RegisterEvent.ChangeAgeFocus(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { calendarState.show() }
                        .focusRequester(focusRequesters[9])
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
