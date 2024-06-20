package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

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
	fun `cant create a book because title is empty`() {
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
				isBadRequest()
			}
		}

	}

	@Test
	fun `cant create a book because title is spaces only`() {
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
				isBadRequest()
			}
		}

	}

	@Test
	fun `cant create a book because title is null`() {
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
	fun `cant create a book because genres is empty`() {
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
				isBadRequest()
			}
		}

	}

	@Test
	fun `cant create a book because genres is null`() {
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

}
