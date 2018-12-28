package com.ksh.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.ksh.Spring_Data_Mongo.SpringDataMongoApplication;
import com.ksh.documents.User;
import com.ksh.services.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringDataMongoApplication.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private UserController userController;

	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contexLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	@Test
	public void testService() throws Exception {
		String url = "/user/testService";
		this.mockMvc.perform(get(url)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Services")));
	}

	@Test
	public void testFindAll() throws Exception {
		String url = "/user/findAll";
		User user = getDummyObject();
		List<User> users = Arrays.asList(user);
		when(userService.findAll()).thenReturn(users);
		mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].fName", is(user.getfName())));
	}

	@Test
	public void testSave() throws Exception {
		String url = "/user/save";
		User user1 = getDummyObject();
		User user2 = getDummyObject();
		user2.setId("12345");
		String jsonString = mapToJson(user1);
		System.out.println("----------------------------------------------"+jsonString);
		System.out.println("----------------------------------------------"+mockMvc);
		when(userService.save(user1)).thenReturn(user2);
		mockMvc.perform(post(url).accept(MediaType.APPLICATION_JSON).content(jsonString).contentType(MediaType.APPLICATION_JSON));
//				.andExpect(status().isOk());
//				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$.resMsg", is("User created Successfully.")));
	}

	@SuppressWarnings("deprecation")
	private String mapToJson(User obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JSR310Module());
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper.writeValueAsString(obj);
	}

	private User getDummyObject() {
		User user = new User();
		user.setActive(true);
		user.setBirthDate(LocalDate.parse("23-Dec-1988", DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
		user.setEmail("ks@ks.com");
		user.setfName("Kshitij");
		user.setlName("Solanki");
		user.setPinCode(123456);
		return user;
	}

}
