package org.interswitch.billsservice.DTOs;

import lombok.Data;

@Data
public class RestResponsePojo<T> {

    private String message;
    private Boolean success = false;
    private T data;
    private Integer status = 200;
}
