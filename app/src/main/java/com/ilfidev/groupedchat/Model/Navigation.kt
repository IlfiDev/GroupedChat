package com.ilfidev.groupedchat.Model

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilfidev.groupedchat.ChatScreen
import com.ilfidev.groupedchat.GroupScreen
import com.ilfidev.groupedchat.LoginScreenBacground
import com.ilfidev.groupedchat.LoginScreenLayout

import com.ilfidev.groupedchat.ViewModel.GroupsViewModel

@Composable
fun Navigation (){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route){
        composable(route = Screen.LoginScreen.route){
            LoginScreenLayout(navController = navController)
        }
        composable(route = Screen.GroupsScreen.route){
            GroupScreen(navController)
        }
        composable(
            route = Screen.ChatScreen.route
        ){
            ChatScreen()
        }
    }
}