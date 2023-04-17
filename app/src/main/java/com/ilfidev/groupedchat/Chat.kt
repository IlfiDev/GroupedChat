package com.ilfidev.groupedchat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


//@Composable
//fun DemoFullChat() {
//
//    val messages = remember { mutableStateListOf<ChatMessage>() }
//    val sdf = remember { SimpleDateFormat("hh:mm", Locale.ROOT) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xffFBE9E7))
//    ) {
//
//        ChatAppbar()
//
//
//        val scrollState = rememberLazyListState()
//        val coroutineScope = rememberCoroutineScope()
//
//        LazyColumn(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth(),
//            state = scrollState,
//            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
//        ) {
//            items(messages) { message: ChatMessage ->
//
//                // Messages here
//            }
//        }
//
//        ChatInput(
//            onMessageChange = { messageContent ->
//                messages.add(
//                    ChatMessage(
//                        (messages.size + 1).toLong(),
//                        messageContent,
//                        System.currentTimeMillis()
//                    )
//                )
//
//                coroutineScope.launch {
//                    scrollState.animateScrollToItem(messages.size - 1)
//                }
//
//            }
//        )
//    }
//}

@Composable
fun ChatInput(onMessageChange: Any) {

}

@Composable
fun ChatAppbar() {
    TODO("Not yet implemented")
}
