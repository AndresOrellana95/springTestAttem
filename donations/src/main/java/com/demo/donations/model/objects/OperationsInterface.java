package com.demo.donations.model.objects;

public class OperationsInterface {
    
    private String company;
    private String credit;
    private String country;
    private String execution;
    private Double amount;

    public OperationsInterface() {
    }

    public OperationsInterface(String company, String credit, String country, String execution, Double amount) {
        this.company = company;
        this.credit = credit;
        this.country = country;
        this.execution = execution;
        this.amount = amount;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCredit() {
        return this.credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getExecution() {
        return this.execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public OperationsInterface company(String company) {
        this.company = company;
        return this;
    }

    public OperationsInterface credit(String credit) {
        this.credit = credit;
        return this;
    }

    public OperationsInterface country(String country) {
        this.country = country;
        return this;
    }

    public OperationsInterface execution(String execution) {
        this.execution = execution;
        return this;
    }

    public OperationsInterface amount(Double amount) {
        this.amount = amount;
        return this;
    }
}
