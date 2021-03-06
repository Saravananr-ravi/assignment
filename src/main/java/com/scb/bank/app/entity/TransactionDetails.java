package com.scb.bank.app.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "transaction_details")
public class TransactionDetails implements Serializable {

    public static final long serialVersionUID = 1L;
    @Id
    @Column(name = "transaction_no")
    @TableGenerator(name = "transactionSequence", initialValue = 30000000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "transactionSequence")
    private Long id;

    @Column(name = "account_no")
    private long accountNumber;

    @Column(name = "transaction_info")
    private String transactInfo;

    @Column(name = "transaction_Type")
    private String transactType;

    @Column(name = "transaction_amount")
    private float transactAmount;

    @Column(name = "closing_balance")
    private float closingBalance;

    @Column(name = "transaction_date")
    private LocalDate transactDate;

}
