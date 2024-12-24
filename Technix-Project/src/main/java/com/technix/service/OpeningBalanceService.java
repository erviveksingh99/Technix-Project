package com.technix.service;

import com.technix.entity.OpeningBalance;

public interface OpeningBalanceService {

    public OpeningBalance createOpeningBalance(OpeningBalance openingBalance);

    public OpeningBalance getOpeningBalanceById(int openingBalanceId);
}
