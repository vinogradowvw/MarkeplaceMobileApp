package com.demo.marketplacemobileapp.aboutMe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.marketplacemobileapp.R

@Composable
fun AboutMeHeader(fullName: String, location: String) {
    Column(verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        AboutMeAvatarPicture(drawableResId = R.drawable.avatar)
        AboutMeInfo(fullName, location)
        AboutMeButtons()
    }
}

@Composable
private fun AboutMeInfo(fullName: String, location: String) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = fullName, textAlign = TextAlign.Center, fontSize = 20.sp)
        Text(text = location, color = Color.LightGray, textAlign = TextAlign.Center,
            fontSize = 20.sp)
    }
}

@Composable
private fun AboutMeButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.LightGray,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Black
            ),
            modifier = Modifier.weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(text = "Message")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color.LightGray,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Black
            ),
            modifier = Modifier.weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(text = "Email")
        }
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Black,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Black
            ),
            modifier = Modifier.weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(text = "Follow")
        }
    }
}


@Composable
private fun AboutMeAvatarPicture(drawableResId: Int) {

    val painter = painterResource(id = drawableResId)

    Image(
        painter = painter,
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier.padding(10.dp)
        .clip(CircleShape)
        .size(75.dp)

    )
}