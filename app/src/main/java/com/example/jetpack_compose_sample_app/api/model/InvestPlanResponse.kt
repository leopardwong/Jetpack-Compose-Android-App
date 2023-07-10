package com.example.jetpack_compose_sample_app.api.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class ApiInvestPlanResponse(
    @SerializedName("status") val status: StatusResponse,
    @SerializedName("data") val data: InvestPlanDataResponse,
)
data class InvestPlanDataResponse(
    @SerializedName("plan") val plan: List<InvestPlanResponse>,
)

data class InvestPlanResponse(
    @SerializedName("plan_id") val planId: Int,
    @SerializedName("catagory_id") val categoryId: Int,
    @SerializedName("plan_name") val planName: String,
    @SerializedName("total_amount") val totalAmount: Int,
    @SerializedName("contribution") val contribution: String,
    @SerializedName("gain_lose_amount") val gainLoseAmount: Int,
    @SerializedName("is_profit") val isProfit: String,
)