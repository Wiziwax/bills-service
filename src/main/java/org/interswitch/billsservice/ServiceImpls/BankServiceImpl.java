package org.interswitch.billsservice.ServiceImpls;

import org.interswitch.billsservice.DTOs.AccountDTO;
import org.interswitch.billsservice.DTOs.CustomerDTO;
import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.interswitch.billsservice.DTOs.TransferDTO;
import org.interswitch.billsservice.Entities.BankAccount;
import org.interswitch.billsservice.Entities.Banks;
import org.interswitch.billsservice.Repositories.BankAccountRepository;
import org.interswitch.billsservice.Repositories.BankRepository;
import org.interswitch.billsservice.Services.BankService;
import org.interswitch.billsservice.Utils.OnboardingServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BankAccountRepository bankAccountsRepository;

    @Autowired
    private OnboardingServiceClient onboardingServiceClient;


    @Override
    public Page<Banks> findAllBanks(Pageable pageable) {
        return bankRepository.findAll(pageable);
    }

    @Override
    public String fundAccount(TransferDTO transferDTO) {

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAccountId(transferDTO.getSourceAccountId());
        paymentDTO.setAmount(transferDTO.getTransferAmount());
        paymentDTO.setCustomerNo(transferDTO.getSourceCustomerNo());
        CustomerDTO customer = onboardingServiceClient.getCustomerByCustomerNo(paymentDTO.getCustomerNo())
                .orElseThrow(()-> new RuntimeException(String.format("Customer with customer number %s not found", paymentDTO.getCustomerNo())));


        if(!transferDTO.getFintechAccount()) {
            BankAccount bankAccount  = bankAccountsRepository.findById(transferDTO.getDestinationAccountId())
                    .orElseThrow(() -> new RuntimeException(String.format("Account with id %s wasn't found", transferDTO.getDestinationAccountId())));
        onboardingServiceClient.debitAccount(paymentDTO);
        bankAccount.setAccountBalance(transferDTO.getTransferAmount().add(bankAccount.getAccountBalance()));
        bankAccountsRepository.save(bankAccount);
        }










//        else {
////            CustomerDTO customer = onboardingServiceClient.getCustomerByCustomerNo(paymentDTO.getCustomerNo())
////                    .orElseThrow(()-> new RuntimeException(String.format("Customer with customer number %s not found", paymentDTO.getCustomerNo())));
//
//
//            List<AccountDTO> accounts = onboardingServiceClient.getAllCustomerAccounts(paymentDTO.getCustomerNo());
//            AccountDTO account = accounts.stream()
//                    .filter(acc -> acc.getId().equals(paymentDTO.getAccountId()))
//                    .findFirst()
//                    .orElseThrow(() -> new RuntimeException("Account not found"));
//
//            // Get the balance and the amount to subtract
//            BigDecimal balance = account.getAccountBalance();
//            BigDecimal amountToSubtract = paymentDTO.getAmount().subtract(new BigDecimal("500"));
//
//
//            if (balance.compareTo(amountToSubtract) <= 0) {
//                throw new RuntimeException("Insufficient Balance");
//            }
//            else{
//                BigDecimal remainingBalance = balance.subtract(paymentDTO.getAmount());
//                System.out.println("The remaining balance is " + remainingBalance);
//                onboardingServiceClient.debitAccount(paymentDTO);
//            }
//
//        }


        return "Transaction Successful";
    }

    @Override
    public BankAccount findByAccountNumber(String accountNo) {
        return bankAccountsRepository.findByAccountNo(accountNo)
                .orElseThrow(()-> new RuntimeException(String.format("Could not find Account with Account number %s", accountNo)));
    }

    @Override
    public Page<BankAccount> findAllByBankCode(String bankCode, Pageable pageable) {
//        return bankAccountsRepository.findAll();
        return bankAccountsRepository.findAllByBankCode(bankCode, pageable);
    }
}
