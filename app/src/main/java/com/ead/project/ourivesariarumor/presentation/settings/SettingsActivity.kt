package com.ead.project.ourivesariarumor.presentation.settings

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ead.project.ourivesariarumor.presentation.settings.options.account_setting_screen.AccountSettingScreen
import com.ead.project.ourivesariarumor.presentation.settings.options.add_product_setting_screen.AddProductSettingScreen
import com.ead.project.ourivesariarumor.presentation.theme.OurivesariaRumorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {

    private var settingOption = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()
        setContent {
            OurivesariaRumorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SettingContainerScreen(
                        modifier = Modifier.fillMaxSize(),
                        onFinishAction = { finish() }
                    ) { defaultModifier ->
                        when(settingOption) {
                            ACCOUNT_OPTION -> {
                                AccountSettingScreen(
                                    modifier = defaultModifier
                                )
                            }
                            DESIGN_OPTION -> {

                            }
                            FILTER_OPTION-> {

                            }
                            SEARCH_OPTION -> {

                            }
                            CATEGORIES_OPTION -> {

                            }
                            INVENTORY_OPTION -> {

                            }
                            NEW_PRODUCTS_OPTION -> {
                                AddProductSettingScreen(
                                    modifier = defaultModifier
                                        .padding(
                                            top = 8.dp,
                                            start = 16.dp,
                                            end = 16.dp
                                        )
                                )
                            }
                            REMOVE_PRODUCTS_OPTION -> {

                            }
                        }
                    }
                }
            }
        }
    }

    private fun getExtras() {
        intent.extras?.apply {
            settingOption = this.getInt(SETTINGS_OPTION,-1)
        }
    }

    companion object {
        const val SETTINGS_OPTION = "SETTINGS_OPTION"

        const val ACCOUNT_OPTION = -200
        const val DESIGN_OPTION = -201
        const val FILTER_OPTION = -202
        const val SEARCH_OPTION = -203
        const val CATEGORIES_OPTION = -204

        const val INVENTORY_OPTION = -300
        const val NEW_PRODUCTS_OPTION = -301
        const val REMOVE_PRODUCTS_OPTION = -302

    }
}