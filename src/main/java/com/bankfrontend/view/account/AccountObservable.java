package com.bankfrontend.view.account;

import com.bankfrontend.domain.Observer;

import java.util.Arrays;
import java.util.List;

public interface AccountObservable {

    List<Observer> getObservers();

    default void registerObserver(Observer... observer) {
        getObservers().addAll(Arrays.asList(observer));
    }

    default void notifyObservers() {
        for (Observer o : getObservers()) {
            o.updateComponent();
        }
    }

    default void sleepAndUpdate() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        notifyObservers();
    }

}
