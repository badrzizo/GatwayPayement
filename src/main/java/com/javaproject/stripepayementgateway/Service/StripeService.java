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


    public StripeResponse checkproductreqest(ProductRequest productRequest)
    {
      Stripe.apiKey="stripe.api.key";

        /// Create a PaymentIntent with the order amount and currencye
        SessionCreateParams.LineItem.PriceData.ProductData  productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()

                .setName(productRequest.getName())
                .build();

        /// Create new line item with the above product data and associated price
        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency(productRequest.getCurrency() != null ? productRequest.getCurrency() : "USD")
                .setUnitAmount(productRequest.getAmount())
                .setProductData(productData)
                .build();

        /// Create a new line item with the above price data
        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
                .setPriceData(priceData)
                .setQuantity(productRequest.getQuantity())
                .build();

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(lineItem)
                .setSuccessUrl("https://example.com/success")
                .setCancelUrl("https://example.com/cancel")
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
