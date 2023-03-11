package com.example.unitTest;

import com.example.unitTest.controllers.HomeController;
import com.example.unitTest.entities.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestApplicationTests {

	@Autowired
	private HomeController controller;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void getAll() throws Exception {
		this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getSingle() throws Exception {
		this.mockMvc.perform(get("/user/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void create() throws Exception {
		this.mockMvc.perform(post("/user")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void update() throws Exception {
		this.mockMvc.perform(put("/user/1")).andDo(print()).andExpect(status().isOk());
	}


	/*
	@Test
	public void delete() throws Exception {
		this.mockMvc.perform(delete("/user")).andDo(print()).andExpect(status().isOk());
	}*/

}
