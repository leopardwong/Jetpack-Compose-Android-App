package com.example.jetpack_compose_sample_app.ui

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.*
import androidx.activity.compose.BackHandler
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import com.example.jetpack_compose_sample_app.MainActivity
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.custom.TopAppBarActionButton
import kotlinx.coroutines.launch


fun showWebView(ctx:Context,url: String){
    val builder = CustomTabsIntent.Builder()
    builder.setToolbarColor(ActivityCompat.getColor(ctx, R.color.BottomBarColor))
    builder.setShowTitle(true)

    val pit = PendingIntent.getActivity(
        ctx, 0, Intent(ctx, MainActivity::class.java), PendingIntent.FLAG_MUTABLE
    )
    //builder.setActionButton(icon, "", pit, true)

    //builder.addMenuItem("Menu", pit)

    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(ctx, android.net.Uri.parse(url))
}
