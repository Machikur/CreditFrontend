package com.bankfrontend.service;

import com.bankfrontend.domain.Payment;
import com.bankfrontend.domain.PaymentType;
import com.bankfrontend.uri.PaymentURL;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@UIScope
@SpringComponent
public class PaymentService {

    private final RestTemplate restTemplate;
    private final PaymentURL paymentURL;
    private final UserService userService = UserService.getInstance();

    @Autowired
    public PaymentService(RestTemplate restTemplate, PaymentURL paymentURL) {
        this.restTemplate = restTemplate;
        this.paymentURL = paymentURL;
    }

    public void makePayment(Payment payment, int pinNumber) {
        restTemplate.postForObject(paymentURL.makePaymentURL(userService.getUserId(), pinNumber), payment, Payment.class);
    }

    public List<Payment> getUserPayments(PaymentType paymentType) {
        Payment[] payments = restTemplate.getForObject(paymentURL.getUserPaymentsURL(userService.getUserId()), Payment[].class);
        return sortPayments(payments, paymentType);
    }

    public List<Payment> getAccountPayments(String accountNumber, PaymentType paymentType) {
        Payment[] payments = restTemplate.getForObject(paymentURL.getAccountPaymentsURL(accountNumber), Payment[].class);
        return sortPayments(payments, paymentType);
    }

    private List<Payment> sortPayments(Payment[] paymentsArray, PaymentType paymentType) {
        if (Objects.isNull(paymentsArray) || paymentsArray.length == 0) {
            return new ArrayList<>();
        }
        List<Payment> payments = Arrays.asList(paymentsArray);
        switch (paymentType) {
            case ALL:
                return payments;
            case ACCOUNT:
                return payments.stream()
                        .filter(payment -> (Objects.nonNull(payment.getAccountToId())))
                        .collect(Collectors.toList());
            case CREDIT:
                return payments.stream()
                        .filter(payment -> (Objects.nonNull(payment.getCreditId())))
                        .collect(Collectors.toList());
            default:
                return new ArrayList<>();
        }
    }

}
