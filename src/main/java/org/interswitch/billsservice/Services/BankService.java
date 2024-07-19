package org.interswitch.billsservice.Services;

import org.interswitch.billsservice.DTOs.TransferDTO;
import org.interswitch.billsservice.Entities.BankAccount;
import org.interswitch.billsservice.Entities.Banks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankService {

    Page<Banks> findAllBanks(Pageable pageable);
    String fundAccount(TransferDTO transferDTO);
    BankAccount findByAccountNumber(String accountNo);
    Page<BankAccount> findAllByBankCode(String bankCode, Pageable pageable);

}
