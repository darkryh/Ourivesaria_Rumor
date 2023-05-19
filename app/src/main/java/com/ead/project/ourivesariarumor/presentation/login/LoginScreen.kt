package com.ead.project.ourivesariarumor.presentation.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.presentation.authentication.component.IconState
import com.ead.project.ourivesariarumor.presentation.login.components.HintTextField
import com.ead.project.ourivesariarumor.presentation.login.components.LoginBackground
import com.ead.project.ourivesariarumor.presentation.login.model.LoginState
import com.ead.project.ourivesariarumor.presentation.login.model.UiEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    goToRegisterAction: () -> Unit,
    goToVerifyAction: () -> Unit
) {

    val context = LocalContext.current
    val emailTextField = viewModel.email.value
    val passwordTextField = viewModel.password.value
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value

    val snackBarHostState = remember { SnackbarHostState() }
    var passwordHide by remember { mutableStateOf(true) }

    val onVerifyAction = {
        if (emailState == LoginState.EmailCorrect){
            viewModel.onEvent(LoginEvent.VerifyUser(context,goToVerifyAction))
        }
        else {
            viewModel.onEvent(LoginEvent.VerifyEmail)
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> snackBarHostState.showSnackbar(
                    message = event.message
                )
            }
        }
    }

    Scaffold(
       snackbarHost = {
           SnackbarHost(hostState = snackBarHostState)
       }
    ) { paddingValues ->
        LoginBackground(modifier = Modifier.fillMaxSize())
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HintTextField(
                    text = emailTextField.text,
                    onValueChange = { viewModel.onEvent(LoginEvent.EnteredEmail(it)) },
                    onFocusChange = { viewModel.onEvent(LoginEvent.ChangeEmailFocus(it)) },
                    modifier = Modifier.weight(1f),
                    hint = emailTextField.hint,
                    isHintContentVisible = emailTextField.isHintVisible,
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.inverseSurface
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { onVerifyAction() }
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconState(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    defaultIcon = Icons.Default.AccountBox,
                    icon =
                    when(emailState) {
                        LoginState.EmailCorrect -> Icons.Default.Check
                        LoginState.EmailIncorrect -> Icons.Default.Close
                        else -> { null }
                    },
                    isLoading = emailState == LoginState.EmailRequest,
                    tint =
                    when(emailState) {
                        LoginState.EmailCorrect -> Color.Green
                        LoginState.EmailIncorrect -> Color.Red
                        else -> { LocalContentColor.current }
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedIconButton(
                    modifier = Modifier,
                    onClick = { onVerifyAction() },
                    shape = CircleShape,
                    colors = IconButtonDefaults.outlinedIconButtonColors()
                ) {
                    Icon(
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null
                    )
                }
            }
            AnimatedVisibility(visible = emailState == LoginState.EmailCorrect) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HintTextField(
                        text = passwordTextField.text,
                        onValueChange = { viewModel.onEvent(LoginEvent.EnteredPassword(it)) },
                        onFocusChange = { viewModel.onEvent(LoginEvent.ChangePasswordFocus(it)) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 8.dp),
                        hint = passwordTextField.hint,
                        isHintContentVisible = passwordTextField.isHintVisible,
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.inverseSurface
                        ),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Go
                        ),
                        keyboardActions = KeyboardActions(
                            onGo = { onVerifyAction() }
                        ),
                        visualTransformation = if (passwordHide) PasswordVisualTransformation()
                        else VisualTransformation.None
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = { passwordHide = !passwordHide }) {
                        Icon(
                            painter =
                            painterResource(
                                id = if (passwordHide) R.drawable.ic_password_close
                                else R.drawable.ic_password_open
                            ),
                            contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconState(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        defaultIcon = Icons.Default.Info,
                        icon =
                        when(passwordState) {
                            LoginState.PasswordCorrect -> Icons.Default.Check
                            LoginState.PasswordIncorrect -> Icons.Default.Close
                            else -> { null }
                        },
                        isLoading = passwordState == LoginState.PasswordRequest,
                        tint =
                        when(passwordState) {
                            LoginState.PasswordCorrect -> Color.Green
                            LoginState.PasswordIncorrect -> Color.Red
                            else -> { LocalContentColor.current }
                        }
                    )
                    Spacer(modifier = Modifier.width(56.dp))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "You do not have an account?",
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier
                        .clickable(onClick = goToRegisterAction),
                    text = "Sign up.",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 14.sp
                )
            }
        }
    }
}

