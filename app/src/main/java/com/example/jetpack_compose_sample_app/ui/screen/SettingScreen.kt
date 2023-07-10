package com.example.jetpack_compose_sample_app.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_sample_app.ui.custom.CustomSettingList
import com.example.jetpack_compose_sample_app.ui.viewmodel.SettingScreenViewModel


@Composable
fun SettingScreen(settingScreenViewModel: SettingScreenViewModel = viewModel(),onNavigateToOtherPage: () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .height(LocalConfiguration.current.screenHeightDp.dp + 20.dp),
    ) {
        for (listContent in settingScreenViewModel.listContents) {
            CustomSettingList(listContent.title, listContent.subtitle,null)
        }
        Button(
            onClick = onNavigateToOtherPage,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth(),
            shape = RectangleShape,
            border = BorderStroke(2.dp, Color.Transparent)
        ) {
            Text(text = "Logout",
                modifier = Modifier.padding(0.dp,10.dp),
                color = Color.Black)
        }
    }
}