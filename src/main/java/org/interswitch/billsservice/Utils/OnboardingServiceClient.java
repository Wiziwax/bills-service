package org.interswitch.billsservice.Utils;

import org.interswitch.billsservice.DTOs.AccountDTO;
import org.interswitch.billsservice.DTOs.CustomerDTO;
import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "onboarding-service")
public interface OnboardingServiceClient {

    @GetMapping("/api/accounts/all")
    List<AccountDTO> getAllCustomerAccounts(@RequestParam String customerNo);

    @GetMapping("/api/customer/getbyid")
    List<CustomerDTO> getCustomerById(@RequestParam Long customerId);

    @GetMapping("/api/customer/getbycustno")
    Optional<CustomerDTO> getCustomerByCustomerNo(@RequestParam String customerNo);

    @PutMapping("/api/accounts/debit")
    String debitAccount(@RequestBody PaymentDTO paymentDTO);
}
