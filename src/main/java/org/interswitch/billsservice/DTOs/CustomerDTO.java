package org.interswitch.billsservice.DTOs;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
}