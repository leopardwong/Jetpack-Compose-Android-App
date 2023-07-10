package com.example.jetpack_compose_sample_app.api

open class ApiState<T>(
    val isLoading: Boolean = false,
    val isRefresh: Boolean = false,
    val isFromDBCache: Boolean = false,
    val isSuccess: T? = null,
    val isBizError: Error? = null,
    val isOtherError: Throwable? = null
)