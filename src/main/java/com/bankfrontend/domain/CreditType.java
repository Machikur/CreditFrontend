package com.bankfrontend.domain;

public enum CreditType {
    WEEKLY(7), MONTHLY(30), SIX_MONTH(180), ANNUAL(365);

    private final int days;

    CreditType(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }

}
