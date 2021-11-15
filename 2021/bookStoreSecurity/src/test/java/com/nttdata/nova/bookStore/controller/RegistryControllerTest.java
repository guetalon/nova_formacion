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

import com.nttdata.nova.bookStore.service.IBookRegistryService;


@WebMvcTest(controllers = RegistryController.class)
public class RegistryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IBookRegistryService bookRegistryService;

	@Test
	public void getAllRegistryTest() throws Exception {	  
		RequestBuilder request = MockMvcRequestBuilders
                .get("/registry/get")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
	}
	
	
}
