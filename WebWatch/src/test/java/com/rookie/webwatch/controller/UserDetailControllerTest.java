package com.rookie.webwatch.controller;

import com.rookie.webwatch.dto.UserDetailDTO;

import com.rookie.webwatch.exception.ResourceNotFoundException;

import com.rookie.webwatch.service.UserDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UserDetailControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserDetailService detailService;

    @Test
    public void createUserDetail(){
        UserDetailDTO detailDTO = new UserDetailDTO(20L, 123456789,  "test 20", 47);

        String exampleJson = "{\"id\":\"20L\",\"phone\":\"123456789\",\"address\":\"test 20\",\"user_id\":\"47\"}";

        try {
            when(detailService.saveUserDetail(detailDTO)).thenReturn(detailDTO);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/udetails/udetail")
                .accept(MediaType.APPLICATION_JSON).content(exampleJson)
                .contentType(MediaType.APPLICATION_JSON);
    }
}
