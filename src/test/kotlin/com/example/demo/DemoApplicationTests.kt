package com.example.demo

import com.example.demo.core.Book
import com.example.demo.core.KotlinBook
import jakarta.validation.Validation
import jakarta.validation.ValidatorFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import kotlin.test.assertEquals

@AutoConfigureMockMvc
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private lateinit var mvc: MockMvc

	@Test
	fun `can create a book`() {
		mvc.post("/books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
                            "title" : "Book title",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isCreated()
			}
		}

	}

	@Test
	fun `can create a kotlin book`() {
		mvc.post("/kotlin-books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
                            "title" : "Book title",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isCreated()
			}
		}

	}

	@Test
	fun `can create a book with empty title`() {
		mvc.post("/books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
                            "title" : "",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isCreated()
			}
		}

	}

	@Test
	fun `cant create a kotlin book with empty title`() {
		mvc.post("/kotlin-books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
                            "title" : "",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isBadRequest()
			}
		}

	}

	@Test
	fun `can create a book with spaces only title`() {
		mvc.post("/books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
                            "title" : "    ",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isCreated()
			}
		}

	}

	@Test
	fun `cant create a kotlin book because title is spaces only`() {
		mvc.post("/kotlin-books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
                            "title" : "    ",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isBadRequest()
			}
		}

	}

	@Test
	fun `cant create a book with null title`() {
		mvc.post("/books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isBadRequest()
			}
		}

	}

	@Test
	fun `cant create a book because title is null`() {
		mvc.post("/kotlin-books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : ["Terror", "Suspense"] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isBadRequest()
			}
		}

	}

	@Test
	fun `can create a book with empty genres`() {
		mvc.post("/books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
							"title" : "Book title",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : [] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isCreated()
			}
		}

	}

	@Test
	fun `cant create a kotlin book because genres is empty`() {
		mvc.post("/kotlin-books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
							"title" : "Book title",
							"author" : "Imthe Authornow",
							"price" : 0.69,
							"genres" : [] 
                        }""".trimIndent()
		}.andExpect {
			status {
				isBadRequest()
			}
		}

	}

	@Test
	fun `cant create a book with null genres`() {
		mvc.post("/books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
							"title" : "Book title",
							"author" : "Imthe Authornow",
							"price" : 0.69
                        }""".trimIndent()
		}.andExpect {
			status {
				isBadRequest()
			}
		}

	}

	@Test
	fun `cant create a kotlin book with null genres`() {
		mvc.post("/kotlin-books"){
			contentType = MediaType.APPLICATION_JSON
			content = """{
							"title" : "Book title",
							"author" : "Imthe Authornow",
							"price" : 0.69
                        }""".trimIndent()
		}.andExpect {
			status {
				isBadRequest()
			}
		}

	}

	@Test
	fun `validate beans`() {
		val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
		val validator = factory.validator
		val books = listOf(
			Book(title = "The title", author = "Imthe Authornow", price = 0.69, genres = listOf("Suspense")),
			Book(title = "", author = "Imthe Authornow", price = 0.69, genres = listOf("Suspense")),
			Book(title = "   ", author = "Imthe Authornow", price = 0.69, genres = listOf("Suspense")),
			Book(title = "The title", author = "Imthe Authornow", price = 0.69, genres = emptyList()),
		)
		val kotlinBooks = listOf(
			KotlinBook(title = "The title", author = "Imthe Authornow", price = 0.69, genres = listOf("Suspense")),
			KotlinBook(title = "", author = "Imthe Authornow", price = 0.69, genres = listOf("Suspense")),
			KotlinBook(title = "   ", author = "Imthe Authornow", price = 0.69, genres = listOf("Suspense")),
			KotlinBook(title = "The title", author = "Imthe Authornow", price = 0.69, genres = emptyList()),
		)
		val booksValidations = books.flatMap { validator.validate(it) }
		booksValidations.forEach { println(it.message) }
		booksValidations.ifEmpty { println("There are no validations errors") }
		assertEquals(0, booksValidations.size)

		val kotlinBooksValidations = kotlinBooks.flatMap { validator.validate(it) }
		kotlinBooksValidations.forEach { println(it.message) }
		kotlinBooksValidations.ifEmpty { println("There are no validations errors") }
		assertEquals(3, kotlinBooksValidations.size)

	}

}
