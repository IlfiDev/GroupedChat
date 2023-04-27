package com.ilfidev.groupedchat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ilfidev.groupedchat.Model.Screen
import com.ilfidev.groupedchat.ViewModel.GroupsViewModel

@Composable
fun GroupScreen(navController: NavController){
    ScaffoldSample(navController)
}

@Composable
fun ChatsArray(viewModel: GroupsViewModel, navController: NavController){
    val arr = listOf("User1", "User2", "User3", "User4")
    val arr2 = listOf(arr, arr, arr, arr, arr)
    val groupsArr = listOf(arr2, arr2)
    var counter = 0


    LazyColumn(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp)){
        items(groupsArr) { item ->
            Surface(modifier = Modifier.padding(5.dp)){

                GridBox(viewModel, navController)
            }
        }

    }
}

@Composable
fun ScaffoldSample(navController: NavController, groupsViewModel: GroupsViewModel = viewModel()){

    Scaffold(backgroundColor = Color.Gray,

        drawerContent = {
            Text("Drawer title", modifier = Modifier.padding(16.dp))
            Divider()
            DrawerContent()

        },
        topBar = {
            Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White)
            ) {
            Text(
                text = "This is static content",
            )
        }},
    ) {
        contentPadding ->
        Box(
            modifier = Modifier.padding(contentPadding),
            )

        ChatsArray(viewModel = groupsViewModel, navController)
    }

}
@Composable
fun GridBox(viewmodel: GroupsViewModel, navController: NavController){

    Row(modifier = Modifier.fillMaxSize()) {
        VerticalGrid(
            modifier = Modifier
                .padding(20.dp), offset = 10, columns = 3
        ) {

            ChatPortrait(name = "Oleg", navController)
            ChatPortrait(name = "Ivan", navController)
            ChatPortrait(name = "Igor", navController)
            ChatPortrait(name = "User", navController)
            ChatPortrait(name = "Oleg", navController)
            ChatPortrait(name = "Ivan", navController)
            ChatPortrait(name = "Igor", navController)
            ChatPortrait(name = "User", navController)
            ChatPortrait(name = "AAAA", navController)
            ChatPortrait(name = "SUSER", navController)
        }
    }
}
@Composable
fun DrawerContent(){
    Column(modifier = Modifier.fillMaxSize()){
        DrawerItem("Account")
        DrawerItem("Settings")
        DrawerItem("Other")
        DrawerItem("Share Account")

    }
}

@Composable
fun DrawerItem(text: String){
    Row(modifier = Modifier
        .clickable { }
        .fillMaxWidth()
        .height(50.dp)
        .padding(5.dp),
    horizontalArrangement = Arrangement.Start
        ){
        Image(painterResource(R.drawable.ic_launcher_background), contentDescription = null)
        Text(text)
    }
}


@Composable
fun GroupBox(arr: List<List<String>>){
    //DraggableCardComplex(isRevealed = false, cardOffset =10f , onExpand = { /*TODO*/ }) {

    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        for (item in arr){
//            ChatsRow(item)
        }
    }

}

@Composable
fun VerticalGrid(
    modifier: Modifier = Modifier,
    offset: Int = 0,
    columns: Int = 2,
    content: @Composable () -> Unit
){
    Layout(
        content = content,
        modifier = modifier
    ){
        measurables, constraints ->
        val itemWidth = constraints.maxWidth / columns
        val itemConstraints = constraints.copy(
            minWidth = itemWidth - offset,
            maxWidth = itemWidth,
            maxHeight = constraints.maxHeight,
            minHeight = constraints.minHeight

        )

        val placeables = measurables.map { it.measure(itemConstraints)}
        val height = Math.ceil(placeables.size.toDouble() / columns).toInt()
        layout(itemConstraints.maxWidth,
            placeables.maxOfOrNull { (it.height + offset) * height  } ?: 0){
            var yPosition = 0
            var xPosition = 0
            var rowsCounter = 0
            placeables.forEach{ placeable ->

                if(rowsCounter == columns){
                    xPosition = 0
                    yPosition += placeable.height + offset
                    rowsCounter = 0
                }
                placeable.placeRelative(x = xPosition, y = yPosition)
                rowsCounter += 1
                xPosition += placeable.width + offset


            }
        }
    }
}





@Composable
fun SettingsAndStuff(){

}

@Composable
fun ChatPortrait(name: String, navController: NavController){
    Card(
        modifier = Modifier
            .size(80.dp, 100.dp)
            .clickable { navController.navigate(Screen.ChatScreen.route)}
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