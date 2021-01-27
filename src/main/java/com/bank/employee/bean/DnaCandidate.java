package com.bank.employee.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class DnaCandidate {

    private String name;
    private LocalDateTime joiningDate;
    private Boolean isManger;
}
