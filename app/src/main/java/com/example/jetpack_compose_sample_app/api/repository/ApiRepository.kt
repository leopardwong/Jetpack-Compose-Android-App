package com.example.jetpack_compose_sample_app.api.repository

import com.example.jetpack_compose_sample_app.api.Api
import com.example.jetpack_compose_sample_app.api.ApiState
import com.example.jetpack_compose_sample_app.api.client.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

object ApiRepository {
    val apiService: Api = ApiClient.createRetrofit("https://raw.githubusercontent.com/leopardwong/Android_Data/main/").create(Api::class.java)
    suspend fun getLoginData() = flow {
        val result = apiService.getLoginData()
        emit(ApiState(isSuccess = result))
    }.flowOn(Dispatchers.IO).onStart {
        emit(ApiState(isLoading = true))
    }.catch {
        emit(ApiState(isOtherError = it))
    }

    suspend fun getInvestPlanData() = flow {
        val result = apiService.getInvestPlanData()
        emit(ApiState(isSuccess = result))
    }.flowOn(Dispatchers.IO).onStart {
        emit(ApiState(isLoading = true))
    }.catch {
        emit(ApiState(isOtherError = it))
    }

    suspend fun getInvestSummaryData() = flow {
        val result = apiService.getInvestSummaryData()
        emit(ApiState(isSuccess = result))
    }.flowOn(Dispatchers.IO).onStart {
        emit(ApiState(isLoading = true))
    }.catch {
        emit(ApiState(isOtherError = it))
    }


}
