package org.interswitch.billsservice.RestControllers;

import org.interswitch.billsservice.DTOs.BankAccountResponseDTO;
import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.interswitch.billsservice.DTOs.RestResponsePojo;
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
    public RestResponsePojo<List<BillCategory>> getAllBillCategories() {
        RestResponsePojo<List<BillCategory>> restResponsePojo = new RestResponsePojo<>();
        List<BillCategory> categories = billPaymentService.getAllBillCategories();
        restResponsePojo.setData(categories);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @GetMapping("/billersbycategory")
    public RestResponsePojo<List<Biller>> getBillersByCategory(@RequestParam Long categoryId) {
        RestResponsePojo<List<Biller>> restResponsePojo = new RestResponsePojo<>();
        List<Biller> billers = billPaymentService.getBillersByCategory(categoryId);
        restResponsePojo.setData(billers);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @GetMapping("billers/all")
    public RestResponsePojo<List<Biller>> getAllBillers() {
        RestResponsePojo<List<Biller>> restResponsePojo = new RestResponsePojo<>();
        List<Biller> billers = billPaymentService.getAllBillers();
        restResponsePojo.setData(billers);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @GetMapping("/products")
    public RestResponsePojo<List<Product>> getProductsByBiller(@RequestParam Long billerId) {
        RestResponsePojo<List<Product>> restResponsePojo = new RestResponsePojo<>();
        List<Product> products = billPaymentService.getProductsByBiller(billerId);
        restResponsePojo.setData(products);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @GetMapping("/products/all")
    public RestResponsePojo<List<Product>> getAllProducts() {
        RestResponsePojo<List<Product>> restResponsePojo = new RestResponsePojo<>();
        List<Product> products = billPaymentService.getAllProducts();
        restResponsePojo.setData(products);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @PutMapping("/paybills")
    public RestResponsePojo<String> submitPayment(@RequestBody PaymentDTO paymentDTO) {
        billPaymentService.submitPayment(paymentDTO);
        RestResponsePojo<String> restResponsePojo = new RestResponsePojo<>();
        restResponsePojo.setData("Transaction successful");
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @PutMapping("/transfer")
    public RestResponsePojo<String> transferFunds(@RequestBody TransferDTO transferDTO) {
        String result = bankService.fundAccount(transferDTO);
        RestResponsePojo<String> restResponsePojo = new RestResponsePojo<>();
        restResponsePojo.setData(result);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @GetMapping("/allbanks")
    public RestResponsePojo<Page<Banks>> getAllBanks(Pageable pageable) {
        RestResponsePojo<Page<Banks>> restResponsePojo = new RestResponsePojo<>();
        Page<Banks> banks = bankService.findAllBanks(pageable);
        restResponsePojo.setData(banks);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @GetMapping("/account_no")
    public RestResponsePojo<BankAccountResponseDTO> findByAccountNumber(@RequestParam String accountNo) {
        RestResponsePojo<BankAccountResponseDTO> restResponsePojo = new RestResponsePojo<>();
        BankAccountResponseDTO bankAccount = bankService.findByAccountNumber(accountNo);
        restResponsePojo.setData(bankAccount);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }

    @GetMapping("/findbybankcode")
    public RestResponsePojo<Page<BankAccountResponseDTO>> findByBankCode(@RequestParam String bankCode, Pageable pageable) {
        RestResponsePojo<Page<BankAccountResponseDTO>> restResponsePojo = new RestResponsePojo<>();
        Page<BankAccountResponseDTO> accounts = bankService.findAllByBankCode(bankCode, pageable);
        restResponsePojo.setData(accounts);
        restResponsePojo.setSuccess(true);
        return restResponsePojo;
    }


}
