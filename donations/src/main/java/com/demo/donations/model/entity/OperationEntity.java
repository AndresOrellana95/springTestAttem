package com.demo.donations.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="operation")
public class OperationEntity {

    @Id
    @Column(name="id_operation", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorO")
    @SequenceGenerator(name="IdGeneratorO", sequenceName="operation_seq")
    private Long idOperation;
    @Column(name = "transaction_uuid", nullable=false, length = 50)
    private String transactionUUID;
    @Column(name = "credit_card", nullable=false, length=16)
    private String creditCard;
    @Column(name = "amount", nullable=false)
    private Double amount;
    @Column(name = "month_year", nullable=false)
    private String monthYear;
    @Column(name = "execution", nullable=false)
    private Date execution;
    @ManyToOne
    @JoinColumn(name ="id_user", nullable = false)
    private UserEntity idUser;
    @ManyToOne
    @JoinColumn(name ="id_country", nullable = false)
    private CountryEntity idCountry;
    @ManyToOne
    @JoinColumn(name ="id_company", nullable = false)
    private CompanyEntity idCompany;


    public OperationEntity() {
    }

    public OperationEntity(Long idOperation, String transactionUUID, String creditCard, Double amount, String monthYear, Date execution, UserEntity idUser, CountryEntity idCountry, CompanyEntity idCompany) {
        this.idOperation = idOperation;
        this.transactionUUID = transactionUUID;
        this.creditCard = creditCard;
        this.amount = amount;
        this.monthYear = monthYear;
        this.execution = execution;
        this.idUser = idUser;
        this.idCountry = idCountry;
        this.idCompany = idCompany;
    }

    public Long getIdOperation() {
        return this.idOperation;
    }

    public void setIdOperation(Long idOperation) {
        this.idOperation = idOperation;
    }

    public String getTransactionUUID() {
        return this.transactionUUID;
    }

    public void setTransactionUUID(String transactionUUID) {
        this.transactionUUID = transactionUUID;
    }

    public String getCreditCard() {
        return this.creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMonthYear() {
        return this.monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public Date getExecution() {
        return this.execution;
    }

    public void setExecution(Date execution) {
        this.execution = execution;
    }

    public UserEntity getIdUser() {
        return this.idUser;
    }

    public void setIdUser(UserEntity idUser) {
        this.idUser = idUser;
    }

    public CountryEntity getIdCountry() {
        return this.idCountry;
    }

    public void setIdCountry(CountryEntity idCountry) {
        this.idCountry = idCountry;
    }

    public CompanyEntity getIdCompany() {
        return this.idCompany;
    }

    public void setIdCompany(CompanyEntity idCompany) {
        this.idCompany = idCompany;
    }

    public OperationEntity idOperation(Long idOperation) {
        this.idOperation = idOperation;
        return this;
    }

    public OperationEntity transactionUUID(String transactionUUID) {
        this.transactionUUID = transactionUUID;
        return this;
    }

    public OperationEntity creditCard(String creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public OperationEntity amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public OperationEntity monthYear(String monthYear) {
        this.monthYear = monthYear;
        return this;
    }

    public OperationEntity execution(Date execution) {
        this.execution = execution;
        return this;
    }

    public OperationEntity idUser(UserEntity idUser) {
        this.idUser = idUser;
        return this;
    }

    public OperationEntity idCountry(CountryEntity idCountry) {
        this.idCountry = idCountry;
        return this;
    }

    public OperationEntity idCompany(CompanyEntity idCompany) {
        this.idCompany = idCompany;
        return this;
    }
}
