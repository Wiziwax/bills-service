package org.interswitch.billsservice.ServiceImpls;

import org.interswitch.billsservice.DTOs.AccountDTO;
import org.interswitch.billsservice.DTOs.CustomerDTO;
import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.interswitch.billsservice.DTOs.TransferDTO;
import org.interswitch.billsservice.Entities.BillCategory;
import org.interswitch.billsservice.Entities.Biller;
import org.interswitch.billsservice.Entities.Payment;
import org.interswitch.billsservice.Entities.Product;
import org.interswitch.billsservice.Repositories.BillCategoryRepository;
import org.interswitch.billsservice.Repositories.BillerRepository;
import org.interswitch.billsservice.Repositories.PaymentRepository;
import org.interswitch.billsservice.Repositories.ProductRepository;
import org.interswitch.billsservice.Services.BillPaymentService;
import org.interswitch.billsservice.Utils.OnboardingServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BillPaymentServiceImpl implements BillPaymentService {

    @Autowired
    private BillCategoryRepository billCategoryRepository;

    @Autowired
    private BillerRepository billerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OnboardingServiceClient onboardingServiceClient;

    @Override
    public List<BillCategory> getAllBillCategories() {
        return billCategoryRepository.findAll();
    }

    @Override
    public List<Biller> getBillersByCategory(Long categoryId) {
        return billerRepository.findByBillCategoryId(categoryId);
    }

    @Override
    public List<Biller> getAllBillers() {
        return billerRepository.findAll();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByBiller(Long billerId) {
        return productRepository.findByBillerId(billerId);
    }

    @Override
    public Payment submitPayment(PaymentDTO paymentDTO) {

        CustomerDTO customer = onboardingServiceClient.getCustomerByCustomerNo(paymentDTO.getCustomerNo())
                .orElseThrow(()-> new RuntimeException(String.format("Customer with customer number %s not found", paymentDTO.getCustomerNo())));


        List<AccountDTO> accounts = onboardingServiceClient.getAllCustomerAccounts(paymentDTO.getCustomerNo());
        AccountDTO account = accounts.stream()
                .filter(acc -> acc.getId().equals(paymentDTO.getAccountId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Get the balance and the amount to subtract
        BigDecimal balance = account.getAccountBalance();
        BigDecimal amountToSubtract = paymentDTO.getAmount().subtract(new BigDecimal("500"));


        if (balance.compareTo(amountToSubtract) <= 0) {
            throw new RuntimeException("Insufficient Balance");
        }
        else{
            BigDecimal remainingBalance = balance.subtract(paymentDTO.getAmount());
            System.out.println("The remaining balance is " + remainingBalance);
            onboardingServiceClient.debitAccount(paymentDTO);
        }


        Product product = productRepository.findById(paymentDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Payment payment = new Payment();
        payment.setProductId(product.getId());
        payment.setAmount(paymentDTO.getAmount());
        paymentRepository.save(payment);

        return payment;
    }

    @Override
    public String transferFunds(TransferDTO transferDTO) {
        return "Transferred successfully";
    }
}
