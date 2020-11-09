package com.demo.donations.model.objects;

public class CreditCard {
    
    private String number;
    private String expiration;
    private String secret;
    private String owner;


    public CreditCard() {
    }

    public CreditCard(String number, String expiration, String secret, String owner) {
        this.number = number;
        this.expiration = expiration;
        this.secret = secret;
        this.owner = owner;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpiration() {
        return this.expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public CreditCard number(String number) {
        this.number = number;
        return this;
    }

    public CreditCard expiration(String expiration) {
        this.expiration = expiration;
        return this;
    }

    public CreditCard secret(String secret) {
        this.secret = secret;
        return this;
    }

    public CreditCard owner(String owner) {
        this.owner = owner;
        return this;
    }

}
