package org.interswitch.billsservice.DTOs;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class BankAccountResponseDTO {

    private Long id;
    private String accountNo;
    private Integer branchNo;
    private String createdBy;
    private Date createdDate;
    private String customerNo;
    private String customerName;
    private String customerNIN;
    private String customerBVN;
    private Boolean isActive;
    private Boolean isDebitRestricted;
    private BigDecimal accountBalance;
    private String bankCode;
}
