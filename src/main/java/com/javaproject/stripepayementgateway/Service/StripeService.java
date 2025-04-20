package com.javaproject.stripepayementgateway.Service;


import com.javaproject.stripepayementgateway.dto.ProductRequest;
import com.javaproject.stripepayementgateway.dto.StripeResponse;
import com.stripe.exception.StripeException;

import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Value;
import com.stripe.Stripe;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${stripe.api.key}")
    private String secretKey;


    public StripeResponse checkoutProducts(ProductRequest productRequest)
    {
        Stripe.apiKey= secretKey;


        SessionCreateParams.LineItem.PriceData.ProductData  productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()

                .setName(productRequest.getName())
                .build();


        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(productRequest.getCurrency() != null ? productRequest.getCurrency() : "USD")
                .setUnitAmount(productRequest.getAmount())
                .setProductData(productData)
                .build();



        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setPriceData(priceData)
                .setQuantity(productRequest.getQuantity())
                .build();

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(lineItem)
                .setSuccessUrl("http://localhost:8080/success")
                .setCancelUrl("http://localhost:8080/cancel")
                .build();


        Session session = null;
        try {
            session = Session.create(params);
        } catch (StripeException ex) {
            System.out.println(ex.getMessage());
        }

        return StripeResponse
                .builder()
                .status("success")
                .message("Payment session created successfully")
                .session_id(session.getId())
                .session_url(session.getUrl())
                .build();

    }
}
