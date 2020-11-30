package com.bankfrontend.frontend.service;

import com.bankfrontend.frontend.ConfigurationProject;
import com.bankfrontend.frontend.domain.Payment;
import com.bankfrontend.frontend.domain.PaymentType;
import com.bankfrontend.frontend.uri.PaymentURL;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PaymentService {

    private static PaymentService paymentService;
    private final RestTemplate restTemplate = ConfigurationProject.getInstanceOfRestTemplate();
    private final PaymentURL paymentURL = new PaymentURL();

    private PaymentService() {
    }

    public static PaymentService getInstance() {
        if (paymentService == null) {
            synchronized (PaymentService.class) {
                if (paymentService == null) {
                    paymentService = new PaymentService();
                }
            }
        }
        return paymentService;
    }

    public Payment makePayment(Payment payment, int pinNumber) {
        return restTemplate.postForObject(paymentURL.makePaymentUri(pinNumber), payment, Payment.class);

    }

    public List<Payment> getUserPayments(PaymentType paymentType) {
        Payment[] payments = restTemplate.getForObject(paymentURL.getUserPaymentsUri(UserService.getUserId()), Payment[].class);
        return sortPayments(payments, paymentType);
    }

    public List<Payment> getAccountPayments(String accountNumber, PaymentType paymentType) {
        Payment[] payments = restTemplate.getForObject(paymentURL.getAccountPaymentsUri(accountNumber), Payment[].class);
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
