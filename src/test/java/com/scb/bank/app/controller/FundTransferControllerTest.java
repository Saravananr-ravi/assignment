package com.scb.bank.app.controller;

import com.scb.bank.app.meta.ResponseMessageType;
import com.scb.bank.app.service.impl.FundTransferServiceImpl;
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

@SpringBootTest
public class FundTransferControllerTest {
    @InjectMocks
    FundTransferController fundTransferControllerTest;

    @Mock
    FundTransferServiceImpl fundTransferServiceImplTest;

    MockMvc mockMvc;

    @Test
    @Disabled
    public void doFundTransferTestForSuccess() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(fundTransferControllerTest).build();
        ResultMatcher expect = MockMvcResultMatchers.content().string(ResponseMessageType.TRANSACTION_SUCCEED.getType());
        //ResultMatcher ok = MockMvcResultMatchers.status().isOk();
        Mockito.when(fundTransferServiceImplTest.doFundTransfer(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyFloat())).thenReturn(ResponseMessageType.TRANSACTION_SUCCEED.getType());
        mockMvc.perform(MockMvcRequestBuilders.post("/fund_transfer")
                .param("fromAccountNo", String.valueOf(2000001L))
                .param("toAccountNo", String.valueOf(2000002L))
                .param("amtToTransfer", String.valueOf(100.0))).andExpect(expect);

        /*ResultMatcher registerView = MockMvcResultMatchers.view()
                .name("user-registration");*/
    }
}
