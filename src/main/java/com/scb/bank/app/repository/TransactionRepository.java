package com.scb.bank.app.repository;

import com.scb.bank.app.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDetails, Long> {
      @Query("from TransactionDetails where accountNumber=:accountNumber and transactDate between :start AND :end")
    List<TransactionDetails> getTransactionBetweenDateAndAccountNumber(@Param("accountNumber") long accountNumber, LocalDate start, LocalDate end);
}
