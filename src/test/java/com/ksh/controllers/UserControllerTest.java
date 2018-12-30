package com.ksh.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.ksh.Spring_Data_Mongo.SpringDataMongoApplication;
import com.ksh.documents.User;
import com.ksh.services.UserService;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = SpringDataMongoApplication.class)
@AutoConfigureMockMvc
@EnableWebMvc
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
	public void testFindAll() throws Exception {
		String url = "/user/findAll";
		User user = getDummyObject();
		List<User> users = Arrays.asList(user);
		when(userService.findAll()).thenReturn(users);
		mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].fName", is(user.getfName())));
	}

	@Test
	public void testSaveUpdate() throws Exception {
		String url = "/user/update";
		User user1 = getDummyObject();
		user1.setId("12345");
		Optional<User> optional = Optional.of(user1);
		when(userService.save(user1)).thenReturn(user1);
		String jsonString = mapToJson(user1);
		when(userService.findById(user1.getId())).thenReturn(optional);
		mockMvc.perform(post(url).content(jsonString).contentType(MediaType.APPLICATION_JSON_UTF8).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(optional.get().getId())));
	}

	private String mapToJson(User obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JSR310Module());
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(obj);
	    return requestJson;
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
