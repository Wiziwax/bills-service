package org.interswitch.billsservice.RestControllers;

import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.interswitch.billsservice.DTOs.TransferDTO;
import org.interswitch.billsservice.Entities.*;
import org.interswitch.billsservice.Services.BankService;
import org.interswitch.billsservice.Services.BillPaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/api/bills")
public class BillPaymentController {

    private final BillPaymentService billPaymentService;
    private final BankService bankService;
    public BillPaymentController(BillPaymentService billPaymentService, BankService bankService) {
        this.billPaymentService = billPaymentService;
        this.bankService = bankService;
    }

    @GetMapping("/categories")
    public List<BillCategory> getAllBillCategories() {
        return billPaymentService.getAllBillCategories();
    }

    @GetMapping("/billersbycategory")
    public List<Biller> getBillersByCategory(@RequestParam Long categoryId) {
        return billPaymentService.getBillersByCategory(categoryId);
    }
    @GetMapping("billers/all")
    public List<Biller> getAllBillers(){
        return billPaymentService.getAllBillers();
    }

    @GetMapping("/products")
    public List<Product> getProductsByBiller(@RequestParam Long billerId) {
        return billPaymentService.getProductsByBiller(billerId);
    }

    @GetMapping("/products/all")
    public List<Product> getAllProducts() {
        return billPaymentService.getAllProducts();
    }


    @PutMapping("/paybills")
    public String submitPayment(@RequestBody PaymentDTO paymentDTO) {
        billPaymentService.submitPayment(paymentDTO);
        return "Transaction successful";
    }
    @PutMapping("/transfer")
    public String transferFunds(@RequestBody TransferDTO transferDTO){
        return bankService.fundAccount(transferDTO);
    }

    @GetMapping("/allbanks")
    public Page<Banks> getAllBanks(Pageable pageable){
        return bankService.findAllBanks(pageable);
    }

    @GetMapping("/account_no")
    public BankAccount findByAccountNumber(@RequestParam String accountNo){
        return bankService.findByAccountNumber(accountNo);
    }

    @GetMapping("/findbybankcode")
    public Page<BankAccount> findByBankCode(@RequestParam String bankCode, Pageable pageable){
        return bankService.findAllByBankCode(bankCode, pageable);
    }
}
