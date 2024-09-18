package com.demo.marketplacemobileapp.presentation.ui.composable.post

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.demo.marketplacemobileapp.config.config
import com.demo.marketplacemobileapp.domain.model.Post
import com.demo.marketplacemobileapp.presentation.AboutMeActivity
import com.demo.marketplacemobileapp.presentation.PostActivity

@Composable
fun PostList(posts: List<Post>, activity: Activity) {

    val stateRowX = rememberLazyListState()
    val stateRowY = rememberLazyListState()

    val scope = rememberCoroutineScope()

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

        val evenPosts: List<Post> = posts.filterIndexed { index, _ -> index % 2 == 0 }
        val oddPosts: List<Post> = posts.filterIndexed { index, _ -> index % 2 != 0 }

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = stateRowX
        ) {
            items(evenPosts.size) { i ->
                PostListItem(
                    title = evenPosts[i].name,
                    price = evenPosts[i].product?.price ?: 11111f,
                    drawableResId = evenPosts[i].images[0],
                    postId = evenPosts[i].id,
                    activity = activity
                )
            }
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f),
            state = stateRowY
        ) {
            items(oddPosts.size) { i ->
                PostListItem(
                    title = oddPosts[i].name,
                    price = oddPosts[i].product?.price ?: 11111f,
                    drawableResId = oddPosts[i].images[0],
                    postId = oddPosts[i].id,
                    activity = activity
                )
            }
        }
    }
}


@Composable
fun PostListItem(postId: Long, title: String, price: Float,
                 drawableResId: Long = 1, activity: Activity) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.3f)
        .padding(10.dp)
        .border(
            width = 0.75.dp,
            color = Color.Gray,
            shape = RoundedCornerShape(16.dp)
        ).clickable {
            val intent = Intent(activity, PostActivity::class.java).apply {
                putExtra("POST_ID", postId)
            }
            activity.startActivity(intent)
        }) {
        PostListItemImage(drawableResId, title="image")
        PostListItemInfo(title, price)
    }
}



@Composable
private fun PostListItemInfo(title: String, price: Float) {
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {

        Text(text = title,
            modifier = Modifier.weight(1f))

        Text(text = "$$price",
            textAlign = TextAlign.End)
    }
}

@Composable
private fun PostListItemImage_(drawableResId: Int, title: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()) {
        val painter = painterResource(id = drawableResId)

        val ratio =
            painter.intrinsicSize.width / painter.intrinsicSize.height

        Image(
            painter = painter,
            contentDescription = title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

@Composable
private fun PostListItemImage(imageId: Long, title: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        ) {
        AsyncImage(model = "${config.BASE_URL}/image/${imageId}", contentDescription = title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            onSuccess = { state ->
                val ratio =
                    state.painter.intrinsicSize.width / state.painter.intrinsicSize.height;
                Modifier.aspectRatio(ratio)
            }
        )
    }
}