package com.example.jetpack_compose_sample_app.ui

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ActivityCompat
import com.example.jetpack_compose_sample_app.activity.MainActivity
import com.example.jetpack_compose_sample_app.R


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
