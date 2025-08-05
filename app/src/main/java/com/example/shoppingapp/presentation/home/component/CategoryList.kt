package com.example.shoppingapp.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.model.CategoryModel

@Composable
fun CategoryItem(
    item: CategoryModel,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable(
            onClick = onItemClick,
            indication = null, // 🔥 disables ripple
            interactionSource = remember { MutableInteractionSource() } // required with no indication
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .size(65.dp)
                .border(
                    width = 2.dp,
                    color = if (isSelected) colorResource(R.color.midBrown) else Color.White,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(
                    color = if (isSelected) Color.White.copy(alpha = 0.7f) else Color.LightGray.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(16.dp),
                ),
            model = item.picUrl,
            contentDescription = item.title,
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = item.title,
            color = if (isSelected) colorResource(R.color.darkBrown) else colorResource(R.color.midBrown),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CategoryList(
    categories: List<CategoryModel>,
    onItemClick: (CategoryModel) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(-1) }
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(start = 16.dp ,end = 16.dp, top = 16.dp)
    ) {
        items(categories.size) { index ->
            CategoryItem(
                item = categories[index],
                isSelected = index == selectedIndex,
                onItemClick = {
                    selectedIndex = index
                    onItemClick(categories[index])
                }
            )
        }
    }

}