package org.interswitch.billsservice.Services;

import org.interswitch.billsservice.DTOs.PaymentDTO;
import org.interswitch.billsservice.DTOs.TransferDTO;
import org.interswitch.billsservice.Entities.BillCategory;
import org.interswitch.billsservice.Entities.Biller;
import org.interswitch.billsservice.Entities.Payment;
import org.interswitch.billsservice.Entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface BillPaymentService {
    List<BillCategory> getAllBillCategories();
    List<Biller> getBillersByCategory(Long categoryId);
    List<Biller> getAllBillers();
    List<Product> getAllProducts();
    List<Product> getProductsByBiller(Long billerId);
    Payment submitPayment(PaymentDTO paymentDTO);

    String transferFunds(TransferDTO transferDTO);
}
