package com.ilfidev.groupedchat.View

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.EditText
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ilfidev.groupedchat.ChatBackground
import com.ilfidev.groupedchat.LoginScreenBacground
import com.ilfidev.groupedchat.R

@Preview
@Composable
fun UserInfoFields(){
    var imageUri: Uri? by remember {
        mutableStateOf(null)
    }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var nickname by remember { mutableStateOf("") }
    var realName by remember { mutableStateOf("") }
    LoginScreenBacground()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
        ){
        imageUri?.also {

            val source = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder
                    .createSource(context.contentResolver, it)
            } else {
                TODO("VERSION.SDK_INT < P")
            }
            bitmap = ImageDecoder.decodeBitmap(source)
            bitmap?.also {btm ->
                Log.i("INFO WORKS", "INFO WORKS")
                Image(
                    bitmap = btm!!.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clickable { launcher.launch("image/*") }
                        .clip(CircleShape).border(width = 5.dp, shape = CircleShape, color = Color.Black),
                    )
            }
        }?:run {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                        .size(400.dp)
                        .clickable { launcher.launch("image/*") }
                        .border(2.dp, Color.Gray))
        }

        OutlinedTextField(value = nickname, onValueChange = { nickname = it })
        OutlinedTextField(value = realName, onValueChange = { nickname = it })
    }
}