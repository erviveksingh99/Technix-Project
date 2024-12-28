package com.technix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyTransactionSummary {

    private String transactionDateFormatted;
    private Double debit;
    private Double credit;
}