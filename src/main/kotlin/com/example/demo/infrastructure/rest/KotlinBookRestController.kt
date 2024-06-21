package com.example.demo.infrastructure.rest

import com.example.demo.core.KotlinBook
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/kotlin-books")
class KotlinBookRestController {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody book: KotlinBook): KotlinBook {
        return book
    }
}