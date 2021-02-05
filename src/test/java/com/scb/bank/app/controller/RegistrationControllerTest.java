package com.scb.bank.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.bank.app.entity.Account;
import com.scb.bank.app.entity.User;
import com.scb.bank.app.meta.ResponseMessageType;
import com.scb.bank.app.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class RegistrationControllerTest {


    @InjectMocks
    RegistrationController registrationControllerTest;

    @Mock
    RegistrationServiceImpl registrationServiceImplTest;

    MockMvc mockMvc;


    @Test
    public void doFundRegistrationForSuccess() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(registrationControllerTest).build();
        User user = new User();
        user.setFirstName("saro");
        user.setLastName("ravi");
        user.setAge(23);
        user.setPanNumber("fvrps3099l");
        Account account = new Account();
        account.setAccountBalance(100);
        user.setAccount(account);
        ObjectMapper mapper = new ObjectMapper();
        String request = mapper.writeValueAsString(user);
        ResultMatcher expect = MockMvcResultMatchers.content().string(ResponseMessageType.USER_REGISTRATION.getType());
        //ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        Mockito.when(registrationServiceImplTest.registerUser(Mockito.any())).thenReturn(ResponseMessageType.USER_REGISTRATION.getType());
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)).andExpect(expect);

        /*ResultMatcher registerView = MockMvcResultMatchers.view()
                .name("user-registration");*/
    }
}
