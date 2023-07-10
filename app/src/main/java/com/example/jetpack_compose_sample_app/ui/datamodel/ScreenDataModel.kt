package com.example.jetpack_compose_sample_app.ui.datamodel

//Home Screen
data class ProfileCount(val title: String, val num: Int)
data class ProfileContent(val name:String, val text:String, val phone: String, val email: String, val profileCount: List<ProfileCount>)
data class QuickLinksContent(val title: String, val quickLinksList: List<QuickLinks>)
data class QuickLinks(val title: String, val icon: Int, val action: () -> Unit)
data class ChartText(val title: String, val context: String, val name: String, val time: String, val totalAmount: String, val gainOrLose: String,val id:String)


//Login Screen
data class UserName(val title: String, val textField: String)
data class Password(val title: String, val textField: String)
data class LoginPageText(val title: String, val body: String, val body2: String)
data class LanguageText (val title: String,val showText:String, val langCode: String)
data class TextWithAction (val title: String,val url: String)


//Top bar
data class DropDownMenuText (val title: String,val showText:String)

//Settings Screen
data class ListContent(val title: String, val subtitle: List<String>)

//Portfolio Screen
data class ReviewListContent(val title: String, val reviewItem: List<ReviewItem>)
data class ReviewItem(val title: String, val icon : Int,val action: () -> Unit)
data class ManageListContent(val title: String, val manageItem: List<ManageItem>)
data class ManageItem(val title: String, val icon: Int)

//For You Screen
data class ForYouListContent(val title: String, val description: String, val icon: Int, val image: Int,val action: () -> Unit)

data class BottomNavItem(var title:String, var icon:Int, var screenRoute:String)
