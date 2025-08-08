package com.example.shoppingapp.presentation.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.presentation.detail.component.HeaderSection
import com.example.shoppingapp.presentation.detail.component.ModelSelector
import com.example.shoppingapp.presentation.detail.component.RatingBarRow
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    itemId: Int,
    onBackClick: () -> Unit,
    detailViewModel: DetailScreenViewModel = koinViewModel(),
) {
    val state by detailViewModel.state.collectAsState()

    LaunchedEffect(itemId) {
        detailViewModel.getItemDetail(itemId)
    }

    val item = state.itemModel
    if (item == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Item not found.")
        }
        return
    }


    var selectedImageUrl by remember(item.id) {
        mutableStateOf(item.picUrl.first())
    }

    var selectedModelIndex by remember(item.id) {
        mutableStateOf(0)
    }

    if (item.size.isNotEmpty() && selectedModelIndex >= item.size.size) {
        selectedModelIndex = 0 
    }

    if (state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = colorResource(R.color.darkBrown))
        }
        return
    }

    if (state.error != null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: ${state.error}")
        }
        return
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        HeaderSection(
            selectedImageUrl = selectedImageUrl,
            imageUrls = item.picUrl,
            onBackClick = onBackClick,
        ) {
            selectedImageUrl = it
        }

        //info section
        Column(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = item.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                    color = colorResource(R.color.darkBrown)
                )
                Text(
                    text = "$${item.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(R.color.midBrown)
                )
            }

            Spacer(modifier.padding(vertical = 16.dp))

            SellerInfoSection(
                item
            )

            Spacer(modifier.padding(vertical = 8.dp))

            RatingBarRow(
                rating = item.rating
            )

            Spacer(modifier.padding(vertical = 8.dp))

            ModelSelector(
                models = item.size, selectedModelIndex = selectedModelIndex, onModelSelected = {
                    selectedModelIndex = it
                })
            Spacer(modifier.padding(vertical = 16.dp))

            Text(
                text = "Description",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(R.color.black),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier.padding(vertical = 4.dp))
            Text(
                text = item.description,
                fontSize = 14.sp,
                color = colorResource(R.color.darkBrown),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.size(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.midBrown)
                    )
                ) {
                    Text(
                        text = "Buy Now", style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.size(16.dp))
                IconButton(
                    onClick = {
                        detailViewModel.onEvent(
                            event = DetailScreenEvent.AddItemToCart(
                                id = item.id,
                                quantity = 1,
                                // Defensive check for item.size and selectedModelIndex
                                size = if (item.size.isNotEmpty() && selectedModelIndex < item.size.size) item.size[selectedModelIndex] else ""
                            )
                        )
                    },
                    modifier = Modifier
                        .width(64.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.LightGray.copy(alpha = 0.4f)),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.Transparent
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.basket),
                        contentDescription = null,
                        modifier = Modifier,
                        colorFilter = ColorFilter.tint(Color.Gray),
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SellerInfoSection(item: ItemModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        AsyncImage(
            model = item.sellerPic,
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .background(
                    color = colorResource(R.color.lightGrey), shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = colorResource(R.color.brown),
                    shape = RoundedCornerShape(10.dp)
                )
        )
        Text(
            text = item.sellerName,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            /*Image(
                painter = painterResource(R.drawable.message),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp)
            )
            Image(
                painter = painterResource(R.drawable.call), contentDescription = null
            )*/
            Button(
                onClick = {},
                modifier = Modifier,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.white).copy(0.8f),
                ),
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(R.color.darkBrown)
                )
            ) {
                Text(
                    text = "View Profile",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            }
        }
    }
}
