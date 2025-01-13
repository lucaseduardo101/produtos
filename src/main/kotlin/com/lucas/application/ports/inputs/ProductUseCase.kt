package com.lucas.application.ports.inputs

import com.lucas.application.dto.ProductDTO

interface ProductUseCase {
    fun getAllProducts(): List<ProductDTO>
    fun getProductById(id: Long): ProductDTO
    fun createProduct(productDTO: ProductDTO): ProductDTO
    fun updateProduct(id: Long, productDTO: ProductDTO): ProductDTO
    fun deleteProduct(id: Long)
}
