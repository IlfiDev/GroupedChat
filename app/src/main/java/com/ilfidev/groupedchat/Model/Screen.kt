package com.ilfidev.groupedchat.Model

sealed class Screen (val route: String){
    object GroupsScreen : Screen("groups_screen")
    object ChatScreen : Screen("chat_screen")
    object LoginScreen : Screen("login_screen")
    object AddInofScreen : Screen("add_info_screen")
}