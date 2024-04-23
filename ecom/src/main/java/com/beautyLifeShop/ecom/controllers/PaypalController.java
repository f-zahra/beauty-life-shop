package com.beautyLifeShop.ecom.controllers;
import com.beautyLifeShop.ecom.config.ExceptionHandler;
import com.beautyLifeShop.ecom.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class PaypalController {


    @Autowired
    private PaypalService paypalService;




    @CrossOrigin
    @PostMapping("payment/create")
    public ResponseEntity<?> createPayment (
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description
    ) throws  PayPalRESTException {

            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success";
            Payment payment = paypalService.createPayment(
                    Double.valueOf(amount),
                    currency,
                    method,
                    "sale",
                    description,
                    cancelUrl,
                    successUrl
            );

            for (Links links: payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {

                    return ResponseEntity.ok(links.getHref());
                }
            }

        return ResponseEntity.ok("/");
    }


    @GetMapping("/payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId
    ) throws PayPalRESTException {

            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                return "paymentSuccess";
            }

        return "paymentSuccess";
    }

    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError() {
        return "paymentError";
    }
}
