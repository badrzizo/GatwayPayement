package com.javaproject.stripepayementgateway.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long amount;
    private String currency;
    private String name;
    private Long quantity;
}
