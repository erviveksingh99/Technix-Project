package com.technix.service;

import com.technix.custome.IdNotFoundException;
import com.technix.entity.Contacts;
import com.technix.entity.OpeningBalance;
import com.technix.repository.ContactsRepository;
import com.technix.repository.OpeningBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OpeningBalanceServiceImpl implements OpeningBalanceService {

    @Autowired
    private OpeningBalanceRepository openingBalanceRepo;

    @Autowired
    private ContactsRepository contactsRepo;

    @Override
    public OpeningBalance createOpeningBalance(OpeningBalance openingBalance) {
        try {
            Contacts contacts = contactsRepo.findByLedgerId(openingBalance.getLedgerId());
            openingBalance.setOpeningBalance(contacts.getOppeningBalance());
            return openingBalanceRepo.save(openingBalance);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Opening balance data saving failed Reason: " + e.getMessage());
        }
    }

    @Override
    public OpeningBalance getOpeningBalanceById(int openingBalanceId) {
        return openingBalanceRepo
                .findById(openingBalanceId).orElseThrow(() -> new IdNotFoundException("Opening balance id is not found"));
    }
}
