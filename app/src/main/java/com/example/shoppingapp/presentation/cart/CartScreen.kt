package com.example.shoppingapp.presentation.cart

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.R
import com.example.shoppingapp.presentation.cart.component.CartItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen(
    viewModel: CartScreenViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
        ){
            Image(
                painter = painterResource(
                    R.drawable.ic_delete,
                ),
                contentDescription = "Back",
                modifier = Modifier
                    .clickable {  }
                    .padding(end = 16.dp)
                    .padding(8.dp)
                    .align(Alignment.TopEnd),
                colorFilter = ColorFilter.tint(Color.Red)
            )
            Text(
                text = "Your Cart",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center),
                maxLines = 1
            )
        }

        if (state.cartItems.isEmpty()) {
            Text(
                text = "Cart is Empty",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            state.cartItems.forEachIndexed { index, cartItem ->
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                CartItem(
                    index = index,
                    cartItem = cartItem,
                    onIncrement = {
                        viewModel.onEvent(CartScreenEvent.IncreaseQuantity(cartItem.itemId))
                    },
                    onDecrement = {
                        viewModel.onEvent(CartScreenEvent.DecreaseQuantity(cartItem.itemId))
                    },
                    onRemove = {}
                )
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Item total:",
                    color = Color.Black,
                    fontSize = 14.sp
                )

                Text(
                    text = "${state.cartItems.sumOf { it.quantity }}",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = colorResource(R.color.darkBrown),
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Sub total:",
                    color = Color.Black,
                    fontSize = 14.sp
                )

                Text(
                    text = "$${state.totalPrice}",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = colorResource(R.color.darkBrown),
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Delivery fee:",
                    color = Color.Black,
                    fontSize = 14.sp
                )

                Text(
                    text = "$${10.0}",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    color = colorResource(R.color.darkBrown),
                    fontSize = 14.sp
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .background(Color.Gray.copy(0.2f))
                    .height(1.dp)
            )

            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total Price:",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    fontSize = 16.sp
                )

                Text(
                    text = "$${calculateTotalPrice(state.totalPrice)}",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.darkBrown),
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent),
                border = BorderStroke(2.dp, colorResource(R.color.midBrown))
            ) {
                Text(
                    text = "Check Out (${state.cartItems.sumOf { it.quantity }})", style = MaterialTheme.typography.titleMedium,
                    color = colorResource(R.color.black),
                )
            }
        }

    }

}

fun calculateTotalPrice(subTotal: Double): Double {
    val deliveryFee = 10.0
    return subTotal + deliveryFee
}