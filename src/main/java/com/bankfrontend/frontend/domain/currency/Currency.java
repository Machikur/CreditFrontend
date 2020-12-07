package com.bankfrontend.frontend.domain.currency;

public enum Currency {
    PLN("PLN"), USD("USD"), EUR("EUR"), GBP("GBP");

    private String desc;

    Currency(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}