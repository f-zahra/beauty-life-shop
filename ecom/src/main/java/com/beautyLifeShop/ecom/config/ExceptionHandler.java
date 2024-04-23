package com.beautyLifeShop.ecom.config;



import com.paypal.base.rest.PayPalRESTException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler extends RuntimeException{


    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleCustomException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(PayPalRESTException.class)
    public ResponseEntity<String> handleCustomException(PayPalRESTException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred "+ex.getMessage());
    }






}
