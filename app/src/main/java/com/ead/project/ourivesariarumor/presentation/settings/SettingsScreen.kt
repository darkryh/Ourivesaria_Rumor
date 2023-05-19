package com.ead.project.ourivesariarumor.presentation.settings

import android.content.Intent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ead.project.ourivesariarumor.R
import com.ead.project.ourivesariarumor.app.data.util.system.launchActivity
import com.ead.project.ourivesariarumor.presentation.settings.components.SettingItem
import com.ead.project.ourivesariarumor.presentation.settings.components.SettingSection

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
    ) {
        item {
            SettingSection(
                modifier = modifier
                    .fillMaxWidth(),
                title = "Account"
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Account",
                description = "configuration account preferences",
                contentDescription = null,
                drawableId = R.drawable.ic_account,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.ACCOUNT_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Orders",
                description = "configuration orders preferences",
                contentDescription = null,
                drawableId = R.drawable.ic_order_setting,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.ACCOUNT_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingSection(
                modifier = modifier
                    .fillMaxWidth(),
                title = "Design"
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Design",
                description = "configuration for app design",
                contentDescription = null,
                drawableId = R.drawable.ic_design,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.DESIGN_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingSection(
                modifier = modifier
                    .fillMaxWidth(),
                title = "Filter"
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Search",
                description = "configuration to keywords in settings",
                contentDescription = null,
                drawableId = R.drawable.ic_search,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.SEARCH_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Categories",
                description = "configuration to get preferences on certain category",
                contentDescription = null,
                drawableId = R.drawable.ic_categories,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.CATEGORIES_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingSection(
                modifier = modifier
                    .fillMaxWidth(),
                title = "Seller Settings"
            )
            Spacer(modifier = Modifier.height(8.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Inventory",
                description = "manage products inventory, refill",
                contentDescription = null,
                drawableId = R.drawable.ic_inventory,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.INVENTORY_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "New Products",
                description = "add your new products",
                contentDescription = null,
                drawableId = R.drawable.ic_add_product,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.NEW_PRODUCTS_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            SettingItem(
                modifier = Modifier
                    .fillMaxWidth(),
                title = "Remove Products",
                description = "remove unnecessary products",
                contentDescription = null,
                drawableId = R.drawable.ic_remove_product,
                onClick = {
                    context.launchActivity(intent = Intent(context,SettingsActivity::class.java)
                        .also { it.putExtra(SettingsActivity.SETTINGS_OPTION,SettingsActivity.REMOVE_PRODUCTS_OPTION) })
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}