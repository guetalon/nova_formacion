package com.nttdata.nova.bookStore.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.nttdata.nova.bookStore.service.IEditorialService;


@WebMvcTest(controllers = EditorialController.class)
public class EditorialControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IEditorialService editorialService;
	
	@Test
	public void insertEditorialTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .post("/editorial/create")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"id\": 0, \"nombre\": \"Nova editions\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	
	@Test
	public void insertEditorialIdExceptionTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
				.post("/editorial/create")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"id\": 1, \"nombre\": \"Nova editions\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
	}

		
	@Test
	public void updateEditorialTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .put("/editorial/update")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"id\": 1, \"nombre\": \"Nova editions\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	
	@Test
	public void updateEditorialIdExceptionTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .put("/editorial/update")
                .accept(MediaType.APPLICATION_JSON)
                .content( "{\"id\": 0, \"nombre\": \"Nova editions\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
	}
	
	
	
	@Test
	public void getAllEditorialTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .get("/editorial/get")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	@Test
	public void getEditorialByIdTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .get("/editorial/get/id/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	

	
	@Test
	public void getEditorialByNameTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .get("/editorial/get/name/{name}", "Nova Editions")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn(); 
	}
	
	
	@Test
	public void deleteEditorialTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .delete("/editorial/delete/{id}", 1)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

	}
	
}
