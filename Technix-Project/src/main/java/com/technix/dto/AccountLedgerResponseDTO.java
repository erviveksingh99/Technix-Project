package com.technix.dto;

import com.technix.entity.Account;
import com.technix.entity.Ledger;
import lombok.Data;

import java.util.List;

@Data
public class AccountLedgerResponseDTO {
    private Account account;
    private List<Ledger> ledgers;
}
