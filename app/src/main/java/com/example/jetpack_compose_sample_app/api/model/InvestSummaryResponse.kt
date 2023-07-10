package com.example.jetpack_compose_sample_app.api.model

import com.google.gson.annotations.SerializedName

data class ApiInvestSummaryResponse(
    @SerializedName("status") val status: StatusResponse,
    @SerializedName("data") val data: InvestSummaryDataResponse,
)
data class InvestSummaryDataResponse(
    @SerializedName("invest_summary") val investSummary: List<InvestSummaryResponse>,
)

data class InvestSummaryResponse(
    @SerializedName("category_id") val categoryId: Int,
    @SerializedName("invest_catagory_name") val investCategoryName: String,
    @SerializedName("invest_plan_amount") val investPlanAmount: Int,
)
