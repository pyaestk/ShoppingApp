package com.example.shoppingapp.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.presentation.MainActivity
import com.example.shoppingapp.R
import com.example.shoppingapp.ui.theme.ShoppingAppTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingAppTheme { SplashScreen(
                onClick = {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            ) }
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    SplashScreen(
        onClick = {}
    )
}

@Composable
fun SplashScreen(
    onClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(colorResource(R.color.brown))
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.splash_logo),
            contentDescription = "Logo",
            modifier = Modifier.padding(top = 16.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        Text(
            text = "Welcome to Shopping App",
            color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 26.sp
        )
        Text(
            text = "Browse thousands of products from your favorite stores, " +
                    "all in one place. Discover new arrivals, exclusive deals, and" +
                    " personalized recommendations tailored just for you. Add items " +
                    "to your cart, save your favorites for later, and enjoy a seamless checkout " +
                    "experience",
            color = colorResource(R.color.darkBrown),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            lineHeight = 25.sp,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 16.sp
        )
        Column(
            modifier = Modifier.fillMaxSize().weight(1F),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                modifier = Modifier.padding(top = 16.dp)
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    onClick()
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.midBrown)
                )
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Text(
                text = "Already have an account?",
                color = colorResource(R.color.darkBrown),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top= 16.dp, bottom = 32.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}