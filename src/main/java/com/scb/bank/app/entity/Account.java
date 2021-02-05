package com.scb.bank.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;


@Data
@Cacheable
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    @Id
    @TableGenerator(name = "accountSequence", initialValue = 2000000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "accountSequence")
    @Column(name = "account_id")
    private Long id;
    @Min(
            value = 1,
            message = "User can not be registered with zero account balance"
    )
    @Column(name = "account_balance")
    private float accountBalance;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
