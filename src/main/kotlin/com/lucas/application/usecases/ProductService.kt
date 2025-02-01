package com.lucas.application.usecases

import com.lucas.application.dto.ProductDTO
import com.lucas.application.ports.inputs.ProductUseCase
import com.lucas.application.ports.outputs.ProductRepository
import com.seudominio.domain.entities.Product
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) : ProductUseCase {

    override fun getAllProducts(): List<ProductDTO> =
        removeDuplicates(
            productRepository.findAll()
        )



    override fun getProductById(id: Long): ProductDTO =
        productRepository.findById(id)?.toDTO()
            ?: throw NoSuchElementException("Product not found")

    override fun createProduct(productDTO: ProductDTO): ProductDTO =
        productRepository.save(productDTO.toDomain()).toDTO()

    override fun updateProduct(id: Long, productDTO: ProductDTO): ProductDTO {
        val existingProduct = productRepository.findById(id)
            ?: throw NoSuchElementException("Product not found")
        val updatedProduct = existingProduct.copy(
            name = productDTO.name,
            description = productDTO.description,
            price = productDTO.price
        )
        return productRepository.save(updatedProduct).toDTO()
    }

    override fun deleteProduct(id: Long) {
        if ( productRepository.findById(id) == null) {
            throw NoSuchElementException("Product not found")
        }
        productRepository.delete(id)
    }

    private fun Product.toDTO() = ProductDTO(id, name, description, price)
    private fun ProductDTO.toDomain() = Product(id, name, description, price)

    private fun removeDuplicates(products: List<Product>): List<ProductDTO> {
        val seen = HashSet<Pair<String, Double>>()
        val uniqueProducts = mutableListOf<Product>()

        for (product in products) {
            val key = product.name to product.price
            if (seen.add(key)) {
                uniqueProducts.add(product)
            }
        }

        return uniqueProducts.map { it.toDTO() }
    }
}
