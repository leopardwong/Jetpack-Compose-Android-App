package com.example.jetpack_compose_sample_app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.datamodel.ForYouListContent
import com.example.jetpack_compose_sample_app.ui.showWebView

class ForYouScreenViewModel(context: Context): ViewModel() {
    val forYouListContents: List<ForYouListContent> = listOf(
        ForYouListContent("MPF exclusive offer",
            "Explore and redeem offers",
            R.drawable.ic_percentage,
            R.drawable.gift
        ) {
            showWebView(
                context,
                "https://www.google.com/"
            )
        },
        ForYouListContent("Featured insights",
            "Grasp latest investment insights",
            R.drawable.ic_lightbulb,
            R.drawable.hongkong
        ) {
            showWebView(
                context,
                "https://www.google.com/"
            )
        },
        ForYouListContent("Latest promotions",
            "Browse latest customer offers",
            R.drawable.ic_star_border,
            R.drawable.phone
        ) {
            showWebView(
                context,
                "https://www.google.com/"
            )
        },
    )
}