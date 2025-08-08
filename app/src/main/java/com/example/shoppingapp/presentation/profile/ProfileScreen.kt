package com.example.shoppingapp.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppingapp.R
import com.example.shoppingapp.presentation.profile.component.OrderInfoBoxes
import com.example.shoppingapp.presentation.profile.component.SettingItems
import com.example.shoppingapp.ui.theme.ShoppingAppTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Profile",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            /*AsyncImage(
                model = painterResource(R.drawable.user_profile_image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(
                        CircleShape
                    )
            )*/
            Image(
                painter = painterResource(R.drawable.user_profile_image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "John Doe",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "kasjdfkasdkf@gmail.com",
                    modifier = Modifier.padding(top = 5.dp),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.Gray
                )
            }
        }

        Row(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OrderInfoBoxes(
                count = 39,
                title = "Total Orders",
                modifier = Modifier.weight(1f),
            )

            OrderInfoBoxes(
                count = 2,
                title = "Active Orders",
                modifier = Modifier.weight(1f),
            )

            OrderInfoBoxes(
                count = 3,
                title = "Cancel Orders",
                modifier = Modifier.weight(1f),
            )
        }


        SettingItems(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            title = "Edit Profile",
            icon = R.drawable.ic_edit
        )
        SettingItems(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            title = "Order History",
            icon = R.drawable.ic_history
        )
        SettingItems(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            title = "Shipping Address",
            icon = R.drawable.ic_location
        )
        SettingItems(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            title = "Change Password",
            icon = R.drawable.ic_password
        )
        SettingItems(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            title = "Log Out",
            icon = R.drawable.ic_logout
        )

    }
}


@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
    ShoppingAppTheme {
        ProfileScreen(
            modifier = Modifier,
            paddingValues = PaddingValues(vertical = 16.dp)
        )
    }
}