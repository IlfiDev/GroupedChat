package com.ilfidev.groupedchat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreenBacground(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

    }
}

@Composable
fun LoggingScreenLayout(){

    var isVisibleLog by remember { mutableStateOf(false) }
    var isVisibleReg by remember { mutableStateOf(false)}
    LoginScreenBacground()
    Text("GroupedChat")
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement
            .Center,
        horizontalAlignment = Alignment
            .CenterHorizontally
    ){
        LoginBox()
        RegisterBox()
    }
}

@Composable
fun LoginBox(){
    var isVisible by remember {
        mutableStateOf(false)
    }

    AnimatedVisibility(visible = !isVisible,
        ) {

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
            .clickable {
                isVisible = !isVisible
            },

            ){
            Text("Login")

        }
    }
    AnimatedVisibility(visible = isVisible,
        modifier = Modifier
            .size(300.dp, 300.dp)
    ) {

        Column(
            modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            OutlinedTextField("Login", {})
            OutlinedTextField(value = "Password", onValueChange = {})
        }

    }
}

@Composable
fun RegisterBox(){
    var isVisible by remember { mutableStateOf(false) }
    AnimatedVisibility(visible = !isVisible) {

        Box(modifier = Modifier
            .size(100.dp, 50.dp)
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
            .clickable {
                isVisible = !isVisible
            },

            ){
            Text("Register")

        }
    }
    AnimatedVisibility(visible = isVisible,
        modifier = Modifier
            .size(300.dp, 300.dp)
    ) {

        Column(
            modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            var loginText by remember { mutableStateOf("") }

            var passwordText by remember { mutableStateOf("") }

            var passwordRepeate by remember { mutableStateOf("") }


            OutlinedTextField(value = loginText, {loginText = it}, label = {Text("Login")})
            OutlinedTextField(value = passwordText, onValueChange = {passwordText = it}, label = {Text("Password")})
            OutlinedTextField(value= passwordRepeate, onValueChange = {passwordRepeate = it}, label = {Text("Password Again")})
        }

    }
}