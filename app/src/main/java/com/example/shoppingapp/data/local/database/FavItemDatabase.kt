package com.example.shoppingapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shoppingapp.data.local.dao.FavItemsDao
import com.example.shoppingapp.data.local.entity.FavItemEntity

@Database(entities = [FavItemEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class FavItemDatabase: RoomDatabase() {

    abstract val dao: FavItemsDao

}