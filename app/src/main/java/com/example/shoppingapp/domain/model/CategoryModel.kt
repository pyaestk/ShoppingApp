package com.example.shoppingapp.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryModel(
    val title: String,
    val id: Int,
    val picUrl: String,
): Parcelable
