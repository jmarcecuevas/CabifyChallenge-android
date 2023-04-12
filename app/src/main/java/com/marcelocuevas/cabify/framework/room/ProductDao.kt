package com.marcelocuevas.cabify.framework.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.marcelocuevas.cabify.framework.room.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query(value = "SELECT * FROM product")
    fun getProductsStream(): Flow<List<ProductEntity>>

    @Upsert
    suspend fun saveProducts(products: List<ProductEntity>)

    @Query("UPDATE product SET orderItemId = :orderItemId WHERE productId = :productCode")
    fun updateOrderIdInProduct(productCode: String, orderItemId: String): Int
}
