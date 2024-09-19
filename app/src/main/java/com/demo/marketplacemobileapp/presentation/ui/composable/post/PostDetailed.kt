package com.demo.marketplacemobileapp.presentation.ui.composable.post

import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.demo.marketplacemobileapp.MarketplaceApplication
import com.demo.marketplacemobileapp.config.config
import com.demo.marketplacemobileapp.data.local.entity.CartItem
import com.demo.marketplacemobileapp.domain.model.Post
import com.demo.marketplacemobileapp.presentation.viewModel.cart.CartViewModel


@Composable
fun PostDetailed(post: Post, cartViewModel: CartViewModel) {
    ItemDetailedImages(imageIds = post.images)
    HorizontalDivider(color = Color.Gray, thickness = 1.dp)
    post.product?.let { ProductInfoDetailed(post=post, cartViewModel=cartViewModel) }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemDetailedImages(imageIds: List<Long>) {
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
            AsyncImage(model = "${config.BASE_URL}/image/${imageIds[page]}",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductInfoDetailed(post: Post, cartViewModel: CartViewModel) {
    Spacer(modifier = Modifier.height(5.dp))
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = post.name,
                textAlign = TextAlign.Start,
                fontSize = 30.sp,
                modifier = Modifier.weight(1f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.weight(0.1f))
            Row (
                modifier = Modifier
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    val cartItem = post.product?.let { CartItem(id=post.id, name=post.name, price = it.price, image = post.images[0]) }
                    cartItem?.let { cartViewModel.addItemToCart(it) }
                },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                ) {
                    Text(text = "$${post.product?.price}", fontSize = 20.sp, color = Color.Black)
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
        Text(text = post.description, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(10.dp))
        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(10.dp))
        if (post.tags != null) {
            FlowRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                post.tags.forEach { tag ->
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
}