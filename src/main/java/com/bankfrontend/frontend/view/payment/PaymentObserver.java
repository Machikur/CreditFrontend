package com.bankfrontend.frontend.view.payment;

import com.bankfrontend.frontend.domain.PaymentType;

public interface PaymentObserver {

    void updateComponent(PaymentType paymentType, String accountNumber);
}