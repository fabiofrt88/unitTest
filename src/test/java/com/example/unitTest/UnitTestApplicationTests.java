package com.example.unitTest;

import com.example.unitTest.controllers.HomeController;
import com.example.unitTest.entities.UserEntity;
import com.example.unitTest.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UnitTestApplicationTests {

	@Autowired
	private HomeController controller;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}


	@Test
	public void getAll() throws Exception {
		this.mockMvc.perform(get("/user/")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void getSingle() throws Exception {
		UserEntity user = new UserEntity();
		user.setUserName("Fabio");
		user.setEmail("fabiof@gmail.com");
		user.setPassword("Password");
		userRepository.saveAndFlush(user);
		this.mockMvc.perform(get("/user/" + user.getId())).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void createUserTest() throws Exception {

		UserEntity user = new UserEntity();
		user.setUserName("Fabio");
		user.setEmail("fabioff@gmail.com");
		user.setPassword("Password");
		//userRepository.saveAndFlush(user);
		String userJSON = objectMapper.writeValueAsString(user);

		this.mockMvc.perform(post("/user/create")
				.contentType(MediaType.APPLICATION_JSON)
				.contentType(userJSON))
				.andDo(print());
				//.andExpect(status().isOk())
		        //.andReturn();
	}

	@Test
	public void updateUserTest() throws Exception {
		UserEntity user = new UserEntity();
		user.setUserName("Fabio");
		user.setEmail("fabiofff@gmail.com");
		user.setPassword("Password");
		userRepository.saveAndFlush(user);

		String newName = "Paul";
		user.setUserName(newName);

		String userJSON = objectMapper.writeValueAsString(user);

		this.mockMvc.perform(put("/user/edit/" + user.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	}

	@Test
	public void deleteUserTest() throws Exception {
		UserEntity user = new UserEntity();
		user.setUserName("Fabio");
		user.setEmail("fabioffff@gmail.com");
		user.setPassword("Password");
		userRepository.saveAndFlush(user);
		assertThat(user.getId()).isNotNull();

		this.mockMvc.perform(delete("/user/delete/" + user.getId()))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

	}

}