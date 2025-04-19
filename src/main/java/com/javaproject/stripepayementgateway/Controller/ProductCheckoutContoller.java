package com.javaproject.stripepayementgateway.Controller;


import com.javaproject.stripepayementgateway.Service.StripeService;
import com.javaproject.stripepayementgateway.dto.ProductRequest;
import com.javaproject.stripepayementgateway.dto.StripeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/checkout")
public class ProductCheckoutContoller {

    private StripeService stripeService;

    public ProductCheckoutContoller(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest) {

        StripeResponse stripeResponse = stripeService.checkproductreqest(productRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }


}
