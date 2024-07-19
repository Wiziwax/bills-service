package org.interswitch.billsservice.DTOs;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class AccountDTO {

    private Long id;
    private String accountNo;
    private BigDecimal accountBalance;
    private String customerNo;
}
