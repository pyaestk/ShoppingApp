package com.example.shoppingapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.presentation.home.component.Banner
import com.example.shoppingapp.presentation.home.component.CategoryList
import com.example.shoppingapp.presentation.home.component.ProductItemCard
import com.example.shoppingapp.presentation.home.component.ProfileHeader
import com.example.shoppingapp.presentation.home.component.SearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (ItemModel) -> Unit,
    navigateToCategory: (CategoryModel) -> Unit,
    mainViewModel: HomeScreenViewModel = koinViewModel()
){

    val homeScreenState by mainViewModel.homeScreenState.collectAsState()

    Column {
        ProfileHeader("Dylan")
        Spacer(modifier = Modifier.height(16.dp))
        SearchBar(
            value = "",
            onTextChanged = {}
        )
        Spacer(modifier = Modifier.height(32.dp))

        if (homeScreenState.error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = colorResource(R.color.darkBrown))
            }
        } else {
            LazyColumn(
                state = rememberLazyListState(),
            ) {
                item {
                    if (homeScreenState.banners.isNotEmpty()) {
                        Banner(banners = homeScreenState.banners)
                    }
                }

                item {
                    if (homeScreenState.categories.isNotEmpty()) {
                        Text(
                            text = "Categories",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp)
                                .padding(horizontal = 16.dp)
                        )
                        CategoryList(
                            categories = homeScreenState.categories,
                            onItemClick = navigateToCategory
                        )
                    }
                }
                item {
                    if (homeScreenState.items.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Recommended",
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = "See All",
                                color = colorResource(R.color.darkBrown),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 13.dp))
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            items(
                                count = homeScreenState.items.size,
                            ) { index ->
                                ProductItemCard(
                                    item = homeScreenState.items[index],
                                    onClick = navigateToDetail
                                )
                            }
                        }

                    }
                }
            }
        }
    }





}



