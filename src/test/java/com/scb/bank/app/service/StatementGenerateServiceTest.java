package com.scb.bank.app.service;

import com.scb.bank.app.exception.DateFormatException;
import com.scb.bank.app.exception.DateRangeException;
import com.scb.bank.app.repository.AccountRepository;
import com.scb.bank.app.service.impl.StatementGenerationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatementGenerateServiceTest {

    static long accountNUmber;
    static String start;
    static String end;
    @InjectMocks
    StatementGenerationServiceImpl statementGenerationServiceImplTest;
    @Mock
    AccountRepository accountRepository;

    @Test
    public void getTransactionInfoTestForDateFormatException() {
        accountNUmber = 2000001;
        start = "2021/01/01";
        end = "2021/01/01";
        Assertions.assertThrows(DateFormatException.class, () -> {
            statementGenerationServiceImplTest.getTransactionInfo(accountNUmber, start, end);
        });
    }

    @Test
    @Disabled
    public void getTransactionInfoTestForDateRangeException() {
        accountNUmber = 2000001;
        start = "2021-02-01";
        end = "2021-01-01";
        Assertions.assertThrows(DateRangeException.class, () -> {
            statementGenerationServiceImplTest.getTransactionInfo(accountNUmber, start, end);
        });
    }
}
