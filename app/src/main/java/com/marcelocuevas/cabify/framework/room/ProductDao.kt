package com.marcelocuevas.cabify.framework.room

import androidx.room.*
import com.marcelocuevas.cabify.framework.room.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query(value = "SELECT * FROM product")
    fun getProductsStream(): Flow<List<ProductEntity>>

    @Upsert
    suspend fun upsertProducts(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProductsIfNotExist(products: List<ProductEntity>)

    @Query("UPDATE product SET orderOwnerItemId = :orderItemId WHERE productId = :productCode")
    fun updateOrderIdInProduct(productCode: String, orderItemId: String): Int

    @Query("SELECT COUNT(*) from product")
    fun productsCount(): Int
}
