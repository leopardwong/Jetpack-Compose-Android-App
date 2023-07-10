package com.example.jetpack_compose_sample_app.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_sample_app.ui.custom.CustomForYouList
import com.example.jetpack_compose_sample_app.ui.theme.LightGray
import com.example.jetpack_compose_sample_app.ui.viewmodel.ForYouScreenViewModel

@Composable
fun ForYouScreen(context: Context) {
    val forYouScreenViewModel = ForYouScreenViewModel(context)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .height(LocalConfiguration.current.screenHeightDp.dp)
            .background(LightGray)
    ) {
        CustomForYouList(forYouScreenViewModel.forYouListContents)
    }
}