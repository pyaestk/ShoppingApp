package com.example.shoppingapp.presentation.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.model.ItemModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CategoryScreen(
    categoryScreenViewModel: CategoryScreenViewModel = koinViewModel(),
    category: CategoryModel,
    onBackClick: () -> Unit,
    onItemClick: (ItemModel) -> Unit,
) {

    val screenState by categoryScreenViewModel.categoryScreenState.collectAsState()
    LaunchedEffect(category.id) {
        categoryScreenViewModel.loadItemsByCategory(category.id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        CategoryHeader(category = category, onBackClick = onBackClick)

        Spacer(modifier = Modifier.height(8.dp))

        if (screenState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = colorResource(R.color.darkBrown))
            }
        } else {
            if (screenState.items.isNotEmpty()){
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        items(screenState.items.size) { index ->
                            ProductItemByCategoryCard(
                                item = screenState.items[index],
                                onClick = onItemClick
                            )
                        }
                    }
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No items found",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
        }

    }
}

@Composable
fun CategoryHeader(category: CategoryModel, onBackClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp).statusBarsPadding(),
    ){
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = "Back",
            modifier = Modifier
                .clickable { onBackClick() }
                .align(Alignment.TopStart),
        )

        Text(
            text = category.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
            maxLines = 1
        )
    }


}


@Composable
fun ProductItemByCategoryCard(
    item: ItemModel, onClick: (ItemModel) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onClick(item) },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.4f)),
    ) {
        Column {
            AsyncImage(
                model = item.picUrl[0],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color = Color.White),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
            ) {
                // Star and rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Text(
                        text = "${item.rating}",
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // pushes the price to the end

                Text(
                    text = "$${item.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.midBrown),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}