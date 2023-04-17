package com.ilfidev.groupedchat

import android.annotation.SuppressLint
import android.util.Log
import android.view.FrameMetrics.ANIMATION_DURATION
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
@Preview
@Composable
fun ChatsScreen(){

}


@Preview
@Composable
fun ChatsArray(){
    val arr = listOf("User1", "User2", "User3", "User4")
    val arr2 = listOf(arr, arr, arr, arr, arr)
    val groupsArr = listOf(arr2, arr2, arr2)
    var counter = 0

    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)){
        items(groupsArr){
                item ->

            Surface(
                modifier = Modifier
                    .border(
                        2.dp,
                        Color(0xFFFF0000 + counter * 100),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(0.dp, 10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                if(counter < 3){

                    counter += 1
                }
                Log.i("Color", counter.toString())
                GroupBox(item)
            }
        }
    }
}

@Composable
fun Scaff(){

}
@Composable
fun ScaffoldSample(){

    Scaffold(

        drawerContent = {

        },
        topBar = {
            Box(
            modifier = Modifier
                .fillMaxWidth().height(50.dp).background(Color.White)
            ) {
            Text(
                text = "This is static content",
            )
        }},
    ) {
        contentPadding ->
        Box(modifier = Modifier.padding(contentPadding))

        ChatsArray()
    }

}


@Composable
fun GroupBox(arr: List<List<String>>){
    DraggableCardComplex(isRevealed = false, cardOffset =10f , onExpand = { /*TODO*/ }) {

    }
    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        for (item in arr){
            ChatsRow(item)
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableCardComplex(
    isRevealed: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
) {
    val offsetX by remember { mutableStateOf(0f) }
    val transitionState = remember {
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(transitionState)
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset - offsetX else -offsetX },
    )

    Card(
        modifier = Modifier
            .offset { IntOffset((offsetX + offsetTransition).roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    //TODO
                }
            },
        content = { ChatPortrait(name = "Igor") }
    )
}

@Composable
fun ChatsRow(arr: List<String>){
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
        ){
        for (item in arr){
            ChatPortrait(name = item)
        }
    }
}

@Composable
fun SettingsAndStuff(){

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPortrait(name: String){
    Card(
        modifier = Modifier
            .size(80.dp, 100.dp)
            .clickable { }
        ,
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
            Text("User")
        }

    }
}