package com.ilfidev.groupedchat

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun ChatBackground(){
    Image(painterResource(id = R.drawable.ic_launcher_background), contentDescription = null)
}

@Preview
@Composable
fun UserMessage(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.End){

        Surface(
            shape = RoundedCornerShape(40),
            modifier = Modifier.defaultMinSize(50.dp)
        ){
            Row(horizontalArrangement = Arrangement.Center){
                Text("text" )
            }
        }
    }

}

@Composable
fun OtherMessage(){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Start){

        Surface(
            shape = RoundedCornerShape(40),
            modifier = Modifier.defaultMinSize(50.dp)
        ){
            Row(horizontalArrangement = Arrangement.Center){
                Text("Aboba sussus amogus lol" , modifier = Modifier.background(Color.Blue))
            }
        }
    }
}

@Composable
fun AudioMessage(){

}

@Composable
fun MessagesBlock(){

}
@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ChatScreen(){
    Scaffold(
        topBar = {
                 Row(modifier = Modifier
                     .background(Color.Red)
                     .fillMaxWidth()
                     .height(50.dp)){

                 }
        },
        bottomBar = {
            Row(modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
                ){
                Image(painterResource(id = R.drawable.ic_launcher_background), contentDescription = null)
                TextField("your text", onValueChange = {}, modifier = Modifier.size(300.dp, 25.dp))
                Image(painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)
            }
        }

        ){
        Column(){
            UserMessage()
            OtherMessage()
        }
    }
}