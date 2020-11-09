package com.demo.donations.model.entity;

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
@Table(name = "company")
public class CompanyEntity {
    @Id
    @Column(name="id_company", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorCo")
    @SequenceGenerator(name="IdGeneratorCo", sequenceName="company_seq")
    private Long idCompany;
    @Column(name = "name", nullable=false, length = 100)
    private String name;
    @ManyToOne
    @JoinColumn(name="id_country", nullable=false)
    private CountryEntity country;


    public CompanyEntity() {
    }

    public CompanyEntity(Long idCompany, String name, CountryEntity country) {
        this.idCompany = idCompany;
        this.name = name;
        this.country = country;
    }

    public Long getIdCompany() {
        return this.idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity getCountry() {
        return this.country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public CompanyEntity idCompany(Long idCompany) {
        this.idCompany = idCompany;
        return this;
    }

    public CompanyEntity name(String name) {
        this.name = name;
        return this;
    }

    public CompanyEntity country(CountryEntity country) {
        this.country = country;
        return this;
    }
}