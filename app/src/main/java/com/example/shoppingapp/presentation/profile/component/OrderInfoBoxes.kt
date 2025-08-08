package com.example.shoppingapp.presentation.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.R

@Composable
fun OrderInfoBoxes(
    count: Int,
    title: String,
    modifier: Modifier
) {

    Column(
        modifier = modifier.background(
            color = Color.LightGray.copy(alpha = 0.4f),
            shape = RoundedCornerShape(16.dp)
        ).padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = count.toString(),
            modifier = Modifier,
            color = colorResource(R.color.midBrown),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = title,
            modifier = Modifier,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}