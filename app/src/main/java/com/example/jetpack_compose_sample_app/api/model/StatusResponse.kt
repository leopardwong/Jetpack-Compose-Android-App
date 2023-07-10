package com.example.jetpack_compose_sample_app.api.model

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class StatusResponse(
    @SerializedName("type") val type: String,
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: Int,
    @SerializedName("error") val error: JSONObject,
)