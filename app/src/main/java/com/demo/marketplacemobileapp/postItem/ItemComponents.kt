package com.demo.marketplacemobileapp.postItem

import android.content.Intent
import android.widget.Space
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.demo.marketplacemobileapp.R
import kotlin.random.Random


@Composable
fun ItemPreview(title: String, price: Float, imageUrl: String = "null", drawableResId: Int = 1) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.3f)
        .padding(10.dp)
        .border(
            width = 0.75.dp,
            color = Color.Gray,
            shape = RoundedCornerShape(16.dp)
        )) {
        ItemPreviewPictureTest(drawableResId = drawableResId, title)
        ItemPreviewInfo(title, price)
    }
}

@Composable
fun ItemPreviewList(productIds: List<Int> = listOf(1,2,3,4,5,6,7,8,9,10)) {

    val stateRowX = rememberLazyListState()
    val stateRowY = rememberLazyListState()

    val scope = rememberCoroutineScope()

    var images = listOf(R.drawable.image1, R.drawable.image2)


    LaunchedEffect(stateRowX.firstVisibleItemIndex, stateRowX.firstVisibleItemScrollOffset) {
        if (!stateRowY.isScrollInProgress) {
            stateRowY.scrollToItem(
                stateRowX.firstVisibleItemIndex,
                stateRowX.firstVisibleItemScrollOffset
            )
        }
    }

    LaunchedEffect(stateRowY.firstVisibleItemIndex, stateRowY.firstVisibleItemScrollOffset) {
        if (!stateRowX.isScrollInProgress) {
            stateRowX.scrollToItem(
                stateRowY.firstVisibleItemIndex,
                stateRowY.firstVisibleItemScrollOffset
            )
        }
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = stateRowX
        ) {
            items(productIds.size) { item ->
                ItemPreview(
                    title = "Some item",
                    price = 100.99f,
                    drawableResId = images[Random.nextInt(2)]
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = stateRowY
        ) {
            items(productIds.size) { item ->
                ItemPreview(
                    title = "Some item",
                    price = 100.99f,
                    drawableResId = images[Random.nextInt(2)]
                )
            }
        }
    }
}

@Composable
private fun ItemPreviewInfo(title: String, price: Float) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {

        Text(text = title,
            modifier = Modifier.weight(1f))

        Text(text = "$$price",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End)
    }
}

@Composable
private fun ItemPreviewPictureTest(drawableResId: Int, title: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        val painter = painterResource(id = drawableResId)

        val aspectRatio = painter.intrinsicSize.width / painter.intrinsicSize.height

        Image(
            painter = painter,
            contentDescription = title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(aspectRatio)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Composable
private fun ItemPreviewPicture(imageUrl: String, title: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        AsyncImage(model = imageUrl, contentDescription = title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            onSuccess = { state ->
                val ratio =
                    state.painter.intrinsicSize.width / state.painter.intrinsicSize.height;
                Modifier.aspectRatio(ratio)
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemDetailedImages(imageIds: List<Int>) {
    val pagerState = rememberPagerState(pageCount = {
        imageIds.size
    })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) { page ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageIds[page]),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductInfoDetailed(name: String, price: Float, description: String, tagNames: List<String>) {
    Spacer(modifier = Modifier.height(5.dp))
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = name,
                textAlign = TextAlign.Start,
                fontSize = 30.sp)
            Spacer(modifier = Modifier.weight(1f))
            Row (
                modifier = Modifier
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(text = "$$price", fontSize = 20.sp, color = Color.Black)
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = description, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            tagNames.forEach { tag ->
                Text(text = tag,
                    Modifier
                        .clip(RoundedCornerShape(50))
                        .background(color = Color.LightGray)
                        .padding(5.dp),
                    fontSize = 15.sp)
                Spacer(modifier = Modifier.width(3.dp))
            }
        }
    }
}