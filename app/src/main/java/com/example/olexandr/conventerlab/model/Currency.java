package com.example.olexandr.conventerlab.model;

public class Currency {
    String currency;
    String buy;
    String sell;
    String organization;

    public String getOrganization() {
        return organization;
    }

    public Currency(String _currency, String _buy, String _sell, String _organization) {
        this.currency = _currency;
        this.buy = _buy;
        this.sell = _sell;
        this.organization = _organization;

    }

    public String getBuy() {
        return buy;
    }

    public String getCurrency() {
        return currency;
    }

    public String getSell() {
        return sell;
    }


}
