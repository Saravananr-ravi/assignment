package com.scb.bank.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.bank.app.entity.TransactionDetails;
import com.scb.bank.app.service.impl.StatementGenerationServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GenerateStatementControllerTest {

    @InjectMocks
    StatementGenerationController statementGenerationControllerTest;

    @Mock
    StatementGenerationServiceImpl statementGenerationServiceTest;

    MockMvc mockMvc;

    @Test
    @Disabled
    public void doFundRegistrationForSuccess() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(statementGenerationControllerTest).build();

        List<TransactionDetails> transactionDetailsList = new ArrayList<>();
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setTransactDate(LocalDate.now());
        transactionDetailsList.add(transactionDetails);
        ObjectMapper mapper = new ObjectMapper();
        String response = mapper.writeValueAsString(transactionDetailsList);
        ResultMatcher expect = MockMvcResultMatchers.content().json(response);


        Mockito.when(statementGenerationServiceTest.getTransactionInfo(Mockito.anyLong(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(transactionDetailsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/generate_statement")
                .param("accountNumber", "2000001")
                .param("start", "2020-01-01")
                .param("end", "2020-01-01")).andExpect(expect);


        /*ResultMatcher registerView = MockMvcResultMatchers.view()
                .name("user-registration");*/
    }
}
