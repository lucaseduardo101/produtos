package com.lucas.infra.web.controllers

import com.lucas.application.dto.ProductDTO
import com.lucas.application.ports.inputs.ProductUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productUseCase: ProductUseCase) {

    @GetMapping
    fun getAllProducts(): List<ProductDTO> = productUseCase.getAllProducts()

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ProductDTO = productUseCase.getProductById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody productDTO: ProductDTO): ProductDTO = productUseCase.createProduct(productDTO)

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody productDTO: ProductDTO): ProductDTO =
        productUseCase.updateProduct(id, productDTO)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProduct(@PathVariable id: Long) = productUseCase.deleteProduct(id)
}
