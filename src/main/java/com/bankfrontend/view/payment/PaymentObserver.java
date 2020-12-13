package com.bankfrontend.view.payment;

import com.bankfrontend.domain.PaymentType;

public interface PaymentObserver {

    void updateComponent(PaymentType paymentType, String accountNumber);
}