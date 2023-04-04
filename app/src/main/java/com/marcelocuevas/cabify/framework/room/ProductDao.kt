package com.marcelocuevas.cabify.framework.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query(value = "SELECT * FROM product")
    fun getProductsStream(): Flow<List<ProductEntity>>

    @Query(value = "DELETE FROM product")
    suspend fun deleteProducts()

    /**
     * Inserts [movies] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnoreProducts(products: List<ProductEntity>): List<Long>

    @Transaction
    suspend fun deleteAndInsert(products: List<ProductEntity>) {
        deleteProducts()
        insertOrIgnoreProducts(products)
    }
}
