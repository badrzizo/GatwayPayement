package com.javaproject.stripepayementgateway.dto;



import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor



public class ProductRequest {
    private Long amount;
    private String currency;
    private String name;
    private Long quantity;


    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getAmount() {
        return amount;
    }

    public Long getQuantity() {
        return quantity;
    }
}
