package org.interswitch.billsservice.DTOs;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TransferDTO {

    Boolean fintechAccount;
    BigDecimal transferAmount;
    String sourceCustomerNo;
    Long sourceAccountId;
    Long bankId;
    String destinationCustomerNo;
    String destinationAccountNo;
    Long destinationAccountId;
}
