package com.example.demo.core

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

data class Book(

    @NotBlank
    val title: String,
    val author: String,
    val price: Double,
    @NotEmpty
    val genres: List<String>,
)
