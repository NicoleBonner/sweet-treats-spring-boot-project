package com.nicole.sweettreatsspringbootproject.controllers;

import com.nicole.sweettreatsspringbootproject.SweetTreatsSpringBootProjectApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@ExtendWith(SpringExtension.class) // Telling me I'm testing a spring boot class - telling junit5 i want spring boot as my target
@ContextConfiguration(classes = SweetTreatsSpringBootProjectApplication.class) //junit knows which class/context we are starting up
@WebAppConfiguration //apply all web app configuration, will start up the application as part of a unit test

public class CourierControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext; //Autowiring- based on dependency injection. A way of seperating classes

    private MockMvc mockMvc; //Starts spring application as part of a unit test

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void getBestCourierForOrder() throws Exception {
        //perform Get Request
        ResultActions result = this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/courier/order/time/{time}/distance/{distance}/fridgeRequired/{fridgeRequired}", LocalTime.of(11, 00), 3.00, true))
                .andDo(
                        print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.courierName").value("Bobby Bikes"));
    }
    }

        //check status code is 200 ok
        //check name
        //check it looks like a courier




