package com.rookie.webwatch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.rookie.webwatch.dto.UserDTO;

import com.rookie.webwatch.entity.User;
import com.rookie.webwatch.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    private final List<UserDTO> userDTOS = new ArrayList<>();

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void getAllTest() throws Exception {
        when(userService.retrieveUsers()).thenReturn(this.userDTOS);
        this.mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())                                             // Mong muốn Server trả về status 200
                .andExpect(jsonPath("$.size()", is(userDTOS.size())));

    }

    @Test
    public void givenUserId_whenGetUser() throws Exception {
        UserDTO userDTO = new UserDTO("user21", "user@gmail.com","123");

        given(userService.getUser(userDTO.getUser_id())).willReturn(Optional.of(userDTO));

        mockMvc.perform(get("/api/users/9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$", hasSize(1)))
                //.andExpect(jsonPath("userName", is(userDTO.getUserName())));
    }

    @Test
    public void createUser(){
        UserDTO userDTO = new UserDTO("user21", "user@gmail.com","123");

        String exampleJson = "{\"id\":\"16\",\"userName\":\"user21\",\"userEmail\":\"user@gmail.com\",\"userPassword\":\"123\"}";

        when(userService.saveUser(userDTO)).thenReturn(userDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/users/user")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void updateUser() throws Exception {
        String uri = "/api/users/user/10";
        User user = new User();
        user.setUserName("username22");
        user.setUserEmail("useremail@gmail.com");
        user.setUserPassword("123456");

        String inputJson = mapToJson(user);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }


    @Test
    public void deleteUser() throws Exception {
        String uri = "/api/users/user/11";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
