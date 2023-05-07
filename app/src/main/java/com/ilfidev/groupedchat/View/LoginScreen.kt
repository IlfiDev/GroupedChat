package com.ilfidev.groupedchat

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
    var auth = Firebase.auth


    var isVisibleLog by remember { mutableStateOf(false) }
    var isVisibleReg by remember { mutableStateOf(false)}
    LoginScreenBacground()
    Text("GroupedChat")
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment
            .CenterHorizontally
    ){


        Column (verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Transparent)){
            LoginBox(navController, auth)
            RegisterBox(auth)
        }


    }
}

@Composable
fun LoginBox(navController: NavController, auth: FirebaseAuth){
    var loginText by remember { mutableStateOf("") }

    var passwordText by remember { mutableStateOf("") }
    val activity = LocalContext.current as Activity



    Box(modifier = Modifier
        .defaultMinSize(100.dp, 50.dp)
        .border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
        .animateContentSize(),

        ){
        Column(){

            Text("Login")

            Column(
                modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                OutlinedTextField(loginText, { loginText = it})
                OutlinedTextField(value = passwordText, onValueChange = {passwordText = it})

                Button(onClick = {
                    auth.signInWithEmailAndPassword(loginText, passwordText).addOnCompleteListener(activity){
                        if(it.isSuccessful){

                            navController.navigate(Screen.GroupsScreen.route)
                        }
                        else{
                            Toast.makeText(activity, "Wrong login or password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }) {
                    Text("Next")
                }
            }
        }
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun RegisterBox(auth: FirebaseAuth){
    var isVisible by remember { mutableStateOf(false) }

    var loginText by remember { mutableStateOf("") }

    var passwordText by remember { mutableStateOf("") }

    var passwordRepeate by remember { mutableStateOf("") }

    val activity = LocalContext.current as Activity
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


                        OutlinedTextField(value = loginText, {loginText = it}, label = {Text("Login")})
                        OutlinedTextField(value = passwordText, onValueChange = {passwordText = it}, label = {Text("Password")})
                        OutlinedTextField(value= passwordRepeate, onValueChange = {passwordRepeate = it}, label = {Text("Password Again")})

                        Button(onClick = {
                            auth.createUserWithEmailAndPassword(loginText, passwordText).addOnCompleteListener(activity){

                                if(it.isSuccessful){

                                    Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()

                                }
                                else{
                                    Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                                    Log.e("Firebase", it.exception.toString())

                                }
                            }
                        }) {
                            Text("Next")
                        }

                    }
                }
            }

        }

}
