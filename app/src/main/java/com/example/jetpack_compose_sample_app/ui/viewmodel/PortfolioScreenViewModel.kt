package com.example.jetpack_compose_sample_app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.ui.datamodel.ManageItem
import com.example.jetpack_compose_sample_app.ui.datamodel.ManageListContent
import com.example.jetpack_compose_sample_app.ui.datamodel.ReviewItem
import com.example.jetpack_compose_sample_app.ui.datamodel.ReviewListContent
import com.example.jetpack_compose_sample_app.ui.showWebView

class PortfolioScreenViewModel(context: Context):ViewModel() {

    val reviewItem = listOf(
        ReviewItem("Policy & account",
            R.drawable.ic_setting
        ) {
            showWebView(
                context,
                "https://www.google.com/"
            )
        },
        ReviewItem("MPF / Pension balance", R.drawable.ic_setting
        ) {
            showWebView(
                context,
                "https://www.google.com/"
            )
        },
        ReviewItem("Fund / investment choice price", R.drawable.ic_setting
        ) {
            showWebView(context, "https://www.google.com/")
        },
        ReviewItem("e-Statement / e-Notice", R.drawable.ic_setting
        ) {
            showWebView(
                context,
                "https://www.google.com/"
            )
        },
        ReviewItem("Contribution / asset transfer-in record (MPF / ORSO / Pension)", R.drawable.ic_setting
        ) {
            showWebView(
                context,
                "https://www.google.com/"
            )
        },
    )
    val reviewListContent = ReviewListContent("Review", reviewItem = reviewItem )

    val manageItem = listOf(
        ManageItem("Manage funds / investment choices",R.drawable.ic_pie_chart_outline),
        ManageItem("Fund / investment choice price",R.drawable.ic_grow_chart),
        ManageItem("e-statement / e-Notice",R.drawable.ic_statement_outline),
    )
    val manageListContent = ManageListContent(
        title = "Manage",
        manageItem = manageItem
    )
}