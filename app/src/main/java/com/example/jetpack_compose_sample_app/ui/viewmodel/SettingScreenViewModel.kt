package com.example.jetpack_compose_sample_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_sample_app.ui.datamodel.ListContent

class SettingScreenViewModel: ViewModel() {
    val listContents: List<ListContent> = listOf(
        ListContent("Manage profile", listOf("Contact information", "Communication language preference", "e-Statement / e-Notice settings")),
        ListContent("Security & preference", listOf("Login & security", "Biometric ID")),
        ListContent("App settings", listOf("Language", "Push notifications")),
        ListContent("Help", listOf("Contact us"))
    )
}