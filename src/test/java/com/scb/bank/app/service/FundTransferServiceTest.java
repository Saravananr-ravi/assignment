package com.scb.bank.app.service;

import com.scb.bank.app.entity.Account;
import com.scb.bank.app.entity.User;
import com.scb.bank.app.meta.ResponseMessageType;
import com.scb.bank.app.repository.AccountRepository;
import com.scb.bank.app.repository.TransactionRepository;
import com.scb.bank.app.service.impl.FundTransferServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class FundTransferServiceTest {


    static long fromAcc;
    static long toAccNo;
    static float amountToTransfer;
    static Account fromAccount;
    static Account toAccount;
    @InjectMocks
    FundTransferServiceImpl fundTransferServiceImplTest;
    @Mock
    AccountRepository accountRepositoryTest;
    @Mock
    TransactionRepository transactionRepository;

    @BeforeAll
    public static void setup() {
        fromAcc = 2000001;
        toAccNo = 2000002;
        amountToTransfer = 100;
        User user = new User(1L, "sar", "ravi", 12, "fvrps3099l", new Account());
        fromAccount = new Account(1L, 100, user);
        toAccount = new Account(2L, 100, user);
    }

    @Test
    public void doFundTransferForPositive() {
        //stabbing
        Mockito.when(accountRepositoryTest.findById(fromAcc)).thenReturn(Optional.of(fromAccount));
        Mockito.when(accountRepositoryTest.findById(toAccNo)).thenReturn(Optional.of(toAccount));
        Mockito.when(accountRepositoryTest.save(fromAccount)).thenReturn(null);
        Mockito.when(accountRepositoryTest.save(toAccount)).thenReturn(null);
        String result = fundTransferServiceImplTest.doFundTransfer(fromAcc, toAccNo, amountToTransfer);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(ResponseMessageType.TRANSACTION_SUCCEED.getType(), result);
    }
}
