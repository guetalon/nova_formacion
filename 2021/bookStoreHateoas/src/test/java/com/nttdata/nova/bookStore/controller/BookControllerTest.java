package com.nttdata.nova.bookStore.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nttdata.nova.bookStore.dto.EditorialDto;
import com.nttdata.nova.bookStore.service.IEditorialService;
import com.nttdata.nova.bookStore.service.impl.BookService;


@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	@MockBean
	private IEditorialService editorialService;
	
	@Test
	public void insertBookTest() throws Exception {	
		EditorialDto editorial = new EditorialDto();
		editorial.setId(Long.valueOf(1));
		editorial.setName("Nova editions");
		
		BDDMockito.given(editorialService.findById(Long.valueOf(1))).willReturn(editorial);
		
		RequestBuilder request = MockMvcRequestBuilders
                .post("/book/create")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"autor\": \"NTT Data\","
                        + "\"descripcion\": \"A book about microservices\", "
                        + "\"editorial\": {\"id\": 1, \"nombre\": \"Nova editions\"},"
                        + "\"id\": 0,"
                        + "\"paginas\": 100,"
                        + "\"publicado\": \"2021-11-11T13:20:18.394Z\","
                        + "\"titulo\": \"An introduction to microservices\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	
	@Test
	public void insertBookIdExceptionTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .post("/book/create")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"autor\": \"NTT Data\","
                        + "\"descripcion\": \"A book about microservices\", "
                        + "\"editorial\": {\"id\": 1, \"nombre\": \"Nova editions\"},"
                        + "\"id\": 1,"
                        + "\"paginas\": 100,"
                        + "\"publicado\": \"2021-11-11T13:20:18.394Z\","
                        + "\"titulo\": \"An introduction to microservices\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
	}
	
	@Test
	public void insertBookDateExceptionTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .post("/book/create")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"autor\": \"NTT Data\","
                        + "\"descripcion\": \"A book about microservices\", "
                        + "\"editorial\": {\"id\": 1, \"nombre\": \"Nova editions\"},"
                        + "\"id\": 0,"
                        + "\"paginas\": 100,"
                        + "\"publicado\": \"2222-11-11T13:20:18.394Z\","
                        + "\"titulo\": \"An introduction to microservices\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
	}
	
	
	@Test
	public void updateBookTest() throws Exception {	  
		EditorialDto editorial = new EditorialDto();
		editorial.setId(Long.valueOf(1));
		editorial.setName("Nova editions");
		
		BDDMockito.given(editorialService.findById(Long.valueOf(1))).willReturn(editorial);
		
		RequestBuilder request = MockMvcRequestBuilders
                .put("/book/update")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"autor\": \"NTT Data\","
                        + "\"descripcion\": \"A book about microservices\", "
                        + "\"editorial\": {\"id\": 1, \"nombre\": \"Nova editions\"},"
                        + "\"id\": 1,"
                        + "\"paginas\": 100,"
                        + "\"publicado\": \"2021-11-11T13:20:18.394Z\","
                        + "\"titulo\": \"An introduction to microservices\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	
	@Test
	public void updateBookIdExceptionTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .put("/book/update")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"autor\": \"NTT Data\","
                        + "\"descripcion\": \"A book about microservices\", "
                        + "\"editorial\": {\"id\": 1, \"nombre\": \"Nova editions\"},"
                        + "\"id\": 0,"
                        + "\"paginas\": 100,"
                        + "\"publicado\": \"2021-11-11T13:20:18.394Z\","
                        + "\"titulo\": \"An introduction to microservices\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
	}
	
	@Test
	public void updateBookDateExceptionTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .put("/book/update")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"autor\": \"NTT Data\","
                        + "\"descripcion\": \"A book about microservices\", "
                        + "\"editorial\": {\"id\": 1, \"nombre\": \"Nova editions\"},"
                        + "\"id\": 1,"
                        + "\"paginas\": 100,"
                        + "\"publicado\": \"2222-11-11T13:20:18.394Z\","
                        + "\"titulo\": \"An introduction to microservices\"}")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
	}
	
	@Test
	public void getAllBooksTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .get("/book/get")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	@Test
	public void getBookByIdTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .get("/book/get/id/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	@Test
	public void getBooksByEditorialTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .post("/book/get/editorial")
                .accept(MediaType.APPLICATION_JSON)        
                .contentType(MediaType.APPLICATION_JSON)
                .content( "{\"id\": 1, \"nombre\": \"Nova editions\"}");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	
	@Test
	public void getBooksByTitleTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .get("/book/get/title/{title}", "Microservice")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn(); 
	}
	
	
	@Test
	public void deleteBookTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .delete("/book/delete/{id}", 1);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

	}
	
}
