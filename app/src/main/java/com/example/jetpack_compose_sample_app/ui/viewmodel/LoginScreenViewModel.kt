package com.example.jetpack_compose_sample_app.ui.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_sample_app.R
import com.example.jetpack_compose_sample_app.api.repository.ApiRepository.getInvestPlanData
import com.example.jetpack_compose_sample_app.api.repository.ApiRepository.getInvestSummaryData
import com.example.jetpack_compose_sample_app.api.repository.ApiRepository.getLoginData
import com.example.jetpack_compose_sample_app.helper.FileStorageHelper

import com.example.jetpack_compose_sample_app.ui.datamodel.*
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginScreenViewModel(context: Context): ViewModel() {
    val fileStorageHelper = FileStorageHelper(context)
    val loginMessage = LoginPageText(
        context.getString(R.string.login),
        context.getString(R.string.login_message_body1),
        context.getString(R.string.login_message_body2))

    val userTextField = UserName(
        context.getString(R.string.username),
        context.getString(R.string.username_input))

    val passwordTextField = Password(
        context.getString(R.string.password),
        context.getString(R.string.password_input))

    val otherText = listOf(
        TextWithAction(context.getString(R.string.privacy),"https://www.google.com.hk/"),
        TextWithAction(context.getString(R.string.copyright),"https://www.google.com.hk/"),
        TextWithAction(context.getString(R.string.terms_and_conditions),"https://www.google.com.hk/"),
        TextWithAction(context.getString(R.string.online_security_information),"https://www.google.com.hk/"),
    )
    val languageTextList = listOf(
        LanguageText(
            context.getString(R.string.english),
            context.getString(R.string.english_show_text),
            "en"),
        LanguageText(
            context.getString(R.string.traditional_chinese),
            context.getString(R.string.traditional_chinese_show_text),
            "zh"),
        LanguageText(
            context.getString(R.string.simplified_chinese),
            context.getString(R.string.simplified_chinese_show_text),
            "zh"),
    )

    val version = context.getString(R.string.version_number)

    fun loadAPI(completed:(Boolean)->Unit){
        viewModelScope.launch {
            getLoginData()
                .filterNot { it.isLoading }
                .map {
                    it.isOtherError?.let {
                        println("====== getLoginData error $it")
                        completed(false)
                        awaitCancellation()
                    }
                    it.isSuccess?.let { response ->
                        fileStorageHelper.storeData("LoginData",response)
                    }
                }
                .flatMapConcat {
                    getInvestPlanData()
                }
                .filterNot { it.isLoading }
                .map {
                    it.isOtherError?.let {
                        println("====== getInvestPlanData error")
                        completed(false)
                        awaitCancellation()
                    }
                    it.isSuccess?.let { response ->
                        fileStorageHelper.storeData("InvestPlanData",response)
                    }
                }
                .flatMapConcat {
                    getInvestSummaryData()
                }
                .filterNot { it.isLoading }
                .collectLatest {
                    it.isOtherError?.let {
                        println("====== getInvestSummaryData error")
                        completed(false)
                        awaitCancellation()
                    }
                    it.isSuccess?.let { response ->
                        fileStorageHelper.storeData("InvestSummaryData",response)
                        completed(true)
                    }
                }
        }
    }
}