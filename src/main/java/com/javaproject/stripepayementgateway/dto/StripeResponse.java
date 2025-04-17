package com.javaproject.stripepayementgateway.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StripeResponse {

    private String status;
    private String message;
    private String session_id;

    private String session_url;
}
