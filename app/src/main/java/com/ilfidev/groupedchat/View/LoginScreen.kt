package com.ilfidev.groupedchat

import androidx.annotation.FloatRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ilfidev.groupedchat.Model.Screen


@Composable
fun LoginScreenBacground(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){

    }
}


@Composable
fun LoginItem(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float
) {
    Layout(
        content = {
            Box(
                modifier = Modifier.layoutId("icon"),
                content = icon
            )
            Box(
                modifier = Modifier.layoutId("text"),
                content = text
            )
        }
    ){
        measurables, constraints ->
        val iconPlaceable = measurables.first{it.layoutId == "icon"}.measure(constraints)
        val textPlaceable = measurables.first{it.layoutId == "text"}.measure(constraints)

        placeTextAddIcon(
            textPlaceable,
            iconPlaceable,
            constraints.maxWidth,
            constraints.maxHeight,
            animationProgress
        )
    }
}

fun MeasureScope.placeTextAddIcon(
    textPlaceable: Placeable,
    iconPlaceable: Placeable,
    width: Int,
    height: Int,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float
): MeasureResult{
    val iconY = (height - iconPlaceable.height) / 2
    val textY = (height - textPlaceable.height) / 2

    val textWidth = textPlaceable.width * animationProgress
    val iconX = (width - textWidth - iconPlaceable.width) / 2
    val textX = iconX + iconPlaceable.width

    return layout(width, height) {
        iconPlaceable.placeRelative(iconX.toInt(), iconY)
        if (animationProgress != 0f){
            textPlaceable.placeRelative(textX.toInt(), textY)
        }
    }
}

@Composable
fun LoginScreenLayout(
navController: NavController
){

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
        var enabled by remember { mutableStateOf(false)}
        var progress by remember{mutableStateOf(0.0f)}

        //val animatedProgress by animateFloatAsState(targetValue = progress)

            //LaunchedEffect(enabled)
            Column (verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Transparent)){
                LoginBox(navController)
                RegisterBox()
            }


    }
}

@Composable
fun LoginBox(navController: NavController){
    var isVisible by remember {
        mutableStateOf(false)
    }



        Box(modifier = Modifier
            .defaultMinSize(100.dp, 50.dp)
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
            .clickable {
                isVisible = !isVisible
            }
            .animateContentSize(),

            ){
            Column(){

                Text("Login")
                if(isVisible){

                    Column(
                        modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){

                        OutlinedTextField("Login", {})
                        OutlinedTextField(value = "Password", onValueChange = {})

                        Button(onClick = { navController.navigate(Screen.GroupsScreen.route)}) {
                            Text("Next")
                        }
                    }
                }
            }
        }

}


@Composable
fun RegisterBox(){
    var isVisible by remember { mutableStateOf(false) }

        Box(modifier = Modifier
            .defaultMinSize(100.dp, 50.dp)
            .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
            .clickable {
                isVisible = !isVisible
            }
            .animateContentSize(),

            ){
            Column(){

                Text("Register")
                if(isVisible){
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

                        Button(onClick = { /*TODO*/ }) {
                            Text("Next")
                        }

                    }
                }
            }

        }
//    AnimatedVisibility(visible = isVisible,
//        modifier = Modifier
//            .size(300.dp, 300.dp)
//    ) {
//
//        Column(
//            modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
//            verticalArrangement = Arrangement.SpaceEvenly,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//            var loginText by remember { mutableStateOf("") }
//
//            var passwordText by remember { mutableStateOf("") }
//
//            var passwordRepeate by remember { mutableStateOf("") }
//
//
//            OutlinedTextField(value = loginText, {loginText = it}, label = {Text("Login")})
//            OutlinedTextField(value = passwordText, onValueChange = {passwordText = it}, label = {Text("Password")})
//            OutlinedTextField(value= passwordRepeate, onValueChange = {passwordRepeate = it}, label = {Text("Password Again")})
//
//            Button(onClick = { /*TODO*/ }) {
//               Text("Next")
//            }
//        }
//
//    }
}