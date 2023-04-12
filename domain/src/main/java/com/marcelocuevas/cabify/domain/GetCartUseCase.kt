package com.marcelocuevas.cabify.domain

import com.marcelocuevas.cabify.data.repository.CartRepository

class GetCartUseCase(
    private val repository: CartRepository
) {

    operator fun invoke() {
        //repository.getc
    }
}