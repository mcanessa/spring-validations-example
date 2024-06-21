package com.example.demo.core

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class KotlinBook(
    @field:NotBlank
    val title: String,
    val author: String,
    val price: Double,
    @field:NotEmpty
    val genres: List<String>)
