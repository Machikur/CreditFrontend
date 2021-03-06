package com.bankfrontend.view.payment;


import com.bankfrontend.domain.PaymentType;

import java.util.Collections;
import java.util.List;

public interface PaymentObservable {

    List<PaymentObserver> getObservers();

    default void registerObserver(PaymentObserver paymentObserver) {
        getObservers().addAll(Collections.singletonList(paymentObserver));
    }

    default void notifyObservers(PaymentType paymentType, String accountNumber) {
        for (PaymentObserver o : getObservers()) {
            o.updateComponent(paymentType, accountNumber);
        }
    }

}