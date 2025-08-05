package com.example.shoppingapp.presentation.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.R

@Composable
fun ModelSelector(
    models: List<String>,
    selectedModelIndex: Int,
    onModelSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier,
        contentPadding = PaddingValues(start = 16.dp, end = 8.dp)
    ) {
        itemsIndexed(models) { index, model ->
            Box(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .height(40.dp)
                    .background(
                        if (index == selectedModelIndex) colorResource(R.color.white) else Color.LightGray.copy(
                            alpha = 0.4f
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = if (index == selectedModelIndex) colorResource(R.color.midBrown) else Color.LightGray.copy(
                            alpha = 0.4f
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable(
                        onClick = {
                            onModelSelected(index)
                        }
                    )
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = model,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

}