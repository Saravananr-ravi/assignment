package com.scb.bank.app.controller;

import com.scb.bank.app.entity.TransactionDetails;
import com.scb.bank.app.service.impl.StatementGenerationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
public class StatementGenerationController {
    @Autowired
    private StatementGenerationServiceImpl statementGenerationService;

    @GetMapping("generate_statement")
    public List<TransactionDetails> generateStatement(@NotBlank @RequestParam long accountNumber,
                                                      @RequestParam String start, @RequestParam String end) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return statementGenerationService.getTransactionInfo(accountNumber, start, end);
    }
}
