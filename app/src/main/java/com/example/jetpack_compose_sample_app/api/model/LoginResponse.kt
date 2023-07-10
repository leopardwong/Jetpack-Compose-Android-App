package com.example.jetpack_compose_sample_app.api.model

import com.google.gson.annotations.SerializedName

data class ApiLoginResponse(
    @SerializedName("status") val status: StatusResponse,
    @SerializedName("data") val data: LoginDataResponse,
)
data class LoginDataResponse(
    @SerializedName("user") val user: User,
    @SerializedName("expires_at") val expiresTime: String,
    @SerializedName("session_token") val sessionToken: String,
    )
data class User(
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("phone") val phone: String,
)

