package com.pedrozimmer.excelToOfx.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Transaction {
    private String type;
    private LocalDate date;
    private String description;
    private String checknum;
    private BigDecimal amount;

}