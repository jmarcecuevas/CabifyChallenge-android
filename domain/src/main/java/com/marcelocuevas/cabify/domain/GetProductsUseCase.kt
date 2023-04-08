package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.model.CartItem
import com.marcelocuevas.cabify.data.model.Product
import com.marcelocuevas.cabify.data.model.ProductCart
import com.marcelocuevas.cabify.data.network.Result
import com.marcelocuevas.cabify.data.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

private const val RETRY_TIME_IN_MILLIS = 15_000L

class GetProductsUseCase @Inject constructor(
    private val repository: ProductsRepository
) {

    suspend operator fun invoke(): Flow<Result<List<Product>>> {
        return repository
                .getProducts()
    }

//    operator fun invoke(): Flow<Result<List<Product>>> {
//        return repository
//            .getProducts()
//            .map {
//                resultOf { it }
//            }
//            .retryWhen { cause, _ ->
//                if (cause is IOException) {
//                    emit(Result.failure(cause))
//                    delay(RETRY_TIME_IN_MILLIS)
//                    true
//                } else {
//                    false
//                }
//            }
//            .catch {
//                emit(Result.failure(it))
//            }
//    }

//    operator fun invoke(): Flow<List<Product>> {
////        combine {
////            repository.getProducts(),repository.getCartItems()
////        }
////
////        )
//
//        return repository.getProducts()
//    }

//    operator fun invoke(
//        query: NewsResourceQuery = NewsResourceQuery(),
//    ): Flow<List<UserNewsResource>> =
////        newsRepository.getNewsResources(
////            query = query,
////        ).mapToUserNewsResources(userDataRepository.userData)
//        flow { emptyList<>() }
}

private fun Flow<List<Product>>.mapToProductCart(
    cartDataStream: Flow<List<CartItem>>,
): Flow<List<ProductCart>> =
//    filterNot { it.isEmpty() }
//        .combine(cartDataStream) { newsResources, userData ->
//            newsResources.mapToUserNewsResources(userData)
//        }
    flow { emptyList<CartItem>() }