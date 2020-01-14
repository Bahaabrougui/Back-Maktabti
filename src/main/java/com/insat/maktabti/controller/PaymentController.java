package com.insat.maktabti.controller;

import com.insat.maktabti.utils.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private StripeClient stripeClient;

    @Autowired
    PaymentController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @CrossOrigin
    @PostMapping("/charge")
    public ResponseEntity<Charge> chargeCard(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        System.out.println("Token is " + token);
        Double amount = Double.parseDouble(request.getHeader("amount"));
        System.out.println("Amount is " + amount);
        Charge charge = this.stripeClient.chargeCreditCard(token, amount);
        return new ResponseEntity<>(charge, HttpStatus.OK);

    }
}
