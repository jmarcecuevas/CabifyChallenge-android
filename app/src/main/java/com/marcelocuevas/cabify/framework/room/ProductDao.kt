package com.marcelocuevas.cabify.framework.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query(value = "SELECT * FROM product")
    fun getProductsStream(): Flow<List<ProductEntity>>

    @Upsert
    suspend fun saveProducts(rockets: List<ProductEntity>)
}
