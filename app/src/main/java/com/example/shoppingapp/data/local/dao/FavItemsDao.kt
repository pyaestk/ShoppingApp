package com.example.shoppingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppingapp.data.local.entity.FavItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavItemsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertItem(item: FavItemEntity)

    @Delete
    suspend fun deleteItem(item: FavItemEntity)

    @Query("SELECT * FROM FavItemEntity")
    fun getItems(): Flow<List<FavItemEntity>>

    @Query("SELECT * FROM FavItemEntity WHERE id = :itemId")
    suspend fun getItemById(itemId: Int): FavItemEntity?

    @Query("DELETE FROM FavItemEntity")
    fun deleteAllItems()

}