package com.example.jetpack_compose_sample_app.helper

import android.content.Context
import com.example.jetpack_compose_sample_app.api.model.ApiInvestPlanResponse
import com.example.jetpack_compose_sample_app.api.model.ApiInvestSummaryResponse
import com.example.jetpack_compose_sample_app.api.model.ApiLoginResponse
import com.google.gson.Gson

class ApiHelper(context: Context) {

    val fileStorageHelper = FileStorageHelper(context)


    fun geLoginData(): ApiLoginResponse? {
        return Gson().fromJson(fileStorageHelper.getData("LoginData"), ApiLoginResponse::class.java)?: null
    }
    fun geInvestPlanData(): ApiInvestPlanResponse? {
        return Gson().fromJson(fileStorageHelper.getData("InvestPlanData"),ApiInvestPlanResponse::class.java)?: null
    }
    fun geInvestSummaryData(): ApiInvestSummaryResponse? {
        return Gson().fromJson(fileStorageHelper.getData("InvestSummaryData"),ApiInvestSummaryResponse::class.java)?: null
    }

}