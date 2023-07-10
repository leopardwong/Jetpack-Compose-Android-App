package com.example.jetpack_compose_sample_app.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.api.model.ApiInvestPlanResponse
import com.example.jetpack_compose_sample_app.api.model.ApiInvestSummaryResponse
import com.example.jetpack_compose_sample_app.api.model.ApiLoginResponse
import com.example.jetpack_compose_sample_app.helper.FileStorageHelper
import com.example.jetpack_compose_sample_app.ui.datamodel.*
import com.example.jetpack_compose_sample_app.ui.showWebView
import com.google.gson.Gson

class HomeScreenViewModel(context: Context):ViewModel() {
    val fileStorageHelper = FileStorageHelper(context)
    val loginData = Gson().fromJson(fileStorageHelper.getData("LoginData"),ApiLoginResponse::class.java)
    val investPlanData = Gson().fromJson(fileStorageHelper.getData("InvestPlanData"),ApiInvestPlanResponse::class.java)
    val investSummaryData = Gson().fromJson(fileStorageHelper.getData("InvestSummaryData"),ApiInvestSummaryResponse::class.java)

    val mpfPlan = investPlanData.data.plan[0]
    val investPlan = investPlanData.data.plan[1]

    val sampleText = context.getText(R.string.sample_text_1)
    val learnMoreLists = listOf(
        context.getText(R.string.learn_more_text_1).toString(),
        context.getText(R.string.learn_more_text_2).toString(),
        context.getText(R.string.learn_more_text_3).toString(),
        context.getText(R.string.learn_more_text_4).toString(),
        context.getText(R.string.learn_more_text_5).toString()
    )

    val templeWithChart1 = ChartText(
        title = investPlan.planName, //API
        context = context.getString(R.string.xx_investment_plus),
        name = "${loginData.data.user.lastname } ${loginData.data.user.firstname}", //API
        time = "As at 1 Feb, 2023",
        totalAmount = investPlan.totalAmount.toString(), //API
        gainOrLose = investPlan.gainLoseAmount.toString(), //API
        id = investPlan.planId.toString()) //API

    val mpfChart = ChartText(
        title = mpfPlan.planName, //API
        context = context.getString(R.string.mpf_personal_account),
        name = "${loginData.data.user.lastname } ${loginData.data.user.firstname}", //API
        time = "As at 1 Feb, 2023",
        totalAmount = mpfPlan.totalAmount.toString(), //API
        gainOrLose = mpfPlan.gainLoseAmount.toString(), //API
        id = mpfPlan.planId.toString()) //API

    val sample2 =context.getString(R.string.sample_text_2)
    val sample3 = context.getString(R.string.sample_text_3)
    val sample4 = context.getString(R.string.sample_text_4)
    val sample5 = context.getString(R.string.sample_text_5)
    val sample6 = context.getString(R.string.learn_more)
    val sampleTextList = listOf(sample2,sample3,sample4,sample5,sample6)

    lateinit var temp: String

    val investSummaryDataList = investSummaryData.data.investSummary //API

    fun profileCount():List<ProfileCount>{
        val profileCountList = mutableListOf<ProfileCount>()
        investSummaryDataList.forEach {
            profileCountList += ProfileCount(it.investCategoryName,it.investPlanAmount)
        }
        return profileCountList
    }
    val profileContent = ProfileContent(
        name = " ${loginData.data.user.username }", //API
        text = context.getString(R.string.intro_text),
        phone = loginData.data.user.phone,
        email = loginData.data.user.email,//API
        profileCount = profileCount())

    val quickLinks = listOf(
        QuickLinks(context.getString(R.string.manage_funds_investment_choices),R.drawable.ic_pie_chart_outline) {
            showWebView(context,"https://www.google.com/")
            println("Manage funds / investment choices")
        },
        QuickLinks(context.getString(R.string.fund_investment_choice_price),R.drawable.ic_grow_chart) {
            showWebView(context,"https://www.google.com/")
            println("Fund / investment choice price")
        },
        QuickLinks(context.getString(R.string.estatement_eNotice),R.drawable.ic_statement_outline) {
            showWebView(context,"https://www.google.com/")
            println("e-statement / e-Notice")
        },
        QuickLinks(context.getString(R.string.mpf_pension_fund_price_alert),R.drawable.ic_notice_alert) {
            showWebView(context,"https://www.google.com/")
            println("MPF / Pension fund price alert")
        },
        QuickLinks(context.getString(R.string.contact_information),R.drawable.ic_contact) {
            showWebView(context,"https://www.google.com/")
            println("Contact information")
        },
        QuickLinks(context.getString(R.string.featured_insights),R.drawable.ic_featured) {
            showWebView(context,"https://www.google.com/")
            println("Featured insights")
        },
    )
    val quickLinksContent = QuickLinksContent(title = context.getString(R.string.quick_links), quickLinksList = quickLinks)

}