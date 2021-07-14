package com.rookie.webwatch.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.webwatch.dto.OrderDTO;

import com.rookie.webwatch.entity.*;
import com.rookie.webwatch.service.OrderService;
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
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    private final List<OrderDTO> orderDTOS = new ArrayList<>();

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void getAllTest() throws Exception {
        when(orderService.retrieveOrders()).thenReturn(this.orderDTOS);
        this.mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())                                             // Mong muốn Server trả về status 200
                .andExpect(jsonPath("$.size()", is(orderDTOS.size())));

    }

    @Test
    public void givenOrderId_whenGetOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO(20, 2020, 16, 1);

        given(orderService.getOrder(orderDTO.getOrder_id())).willReturn(Optional.of(orderDTO));

        mockMvc.perform(get("/api/orders/17")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createOrder() throws Exception {
        OrderDTO orderDTO = new OrderDTO(20, 2020, 16, 1);

        String inputJson = mapToJson(orderDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/order")
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
    }

    @Test
    public void updateOrder() throws Exception {
        String uri = "/api/orders/order/17";
        Order order = new Order();
        User user = new User(16,"user21", "user@gmail.com","123");
        Status status = new Status(1,1,"shipping");
        order.setTotalQty(21);
        order.setTotalPrice(2021);
        order.setUser(user);
        order.setStatus(status);

        String inputJson = mapToJson(order);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int statusCode = mvcResult.getResponse().getStatus();
        assertEquals(200, statusCode);
    }


    @Test
    public void deleteOrder() throws Exception {
        String uri = "/api/orders/order/17";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
