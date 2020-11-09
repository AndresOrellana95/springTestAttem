package com.demo.donations.model.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class DonateRequest {
    
    private Long company;
    private Long country;
    private Double amount;
    private CreditCard creditCard;

    public DonateRequest() {
    }

    public DonateRequest(Long company, Long country, Double amount, CreditCard creditCard) {
        this.company = company;
        this.country = country;
        this.amount = amount;
        this.creditCard = creditCard;
    }

    public Long getCompany() {
        return this.company;
    }

    public void setCompany(Long company) {
        this.company = company;
    }

    public Long getCountry() {
        return this.country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CreditCard getCreditCard() {
        return this.creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public DonateRequest company(Long company) {
        this.company = company;
        return this;
    }

    public DonateRequest country(Long country) {
        this.country = country;
        return this;
    }

    public DonateRequest amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public DonateRequest creditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }
}
