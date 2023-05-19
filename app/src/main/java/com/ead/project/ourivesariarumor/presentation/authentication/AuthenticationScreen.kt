package com.ead.project.ourivesariarumor.presentation.authentication

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.presentation.login.LoginScreen
import com.ead.project.ourivesariarumor.presentation.register.RegisterScreen
import com.ead.project.ourivesariarumor.presentation.verification.VerificationScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthenticationScreen(
    modifier: Modifier = Modifier,
    onFinishAction: () -> Unit
) {
    val pages = listOf(stringResource(id = R.string.login), stringResource(id = R.string.register),
        stringResource(id = R.string.verify))
    val pagerState = rememberPagerState(initialPage = 1)
    val scope = rememberCoroutineScope()

    BackHandler {
        scope.launch {
            if (pagerState.currentPage != 1) {
                pagerState.animateScrollToPage(1)
            }
            else {
                onFinishAction()
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxSize(),
        count = pages.size,
        userScrollEnabled = false
    ) { currentPage ->
        when(currentPage) {
            0 -> {
                VerificationScreen {
                    scope.launch {
                        pagerState.animateScrollToPage(1)
                    }
                }
            }
            1 -> {
                LoginScreen(
                    goToRegisterAction = {
                        scope.launch {
                            pagerState.animateScrollToPage(2)
                        }
                    }
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }

            }
            2 -> {
                RegisterScreen(
                    goToLoginAction = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    }
                ) {
                    scope.launch {
                        pagerState.animateScrollToPage(0)
                    }
                }
            }
        }
    }
}