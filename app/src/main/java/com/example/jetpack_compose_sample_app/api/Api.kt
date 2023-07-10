package com.example.jetpack_compose_sample_app.api

import com.example.jetpack_compose_sample_app.api.model.ApiInvestPlanResponse
import com.example.jetpack_compose_sample_app.api.model.ApiInvestSummaryResponse
import com.example.jetpack_compose_sample_app.api.model.ApiLoginResponse
import retrofit2.http.GET


interface Api {
    @GET("User.json")
    suspend fun getLoginData(): ApiLoginResponse

    @GET("invest_plan.json")
    suspend fun getInvestPlanData(): ApiInvestPlanResponse

    @GET("invest_summary.json")
    suspend fun getInvestSummaryData(): ApiInvestSummaryResponse

}