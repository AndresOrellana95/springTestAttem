package com.demo.donations.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class CountryEntity {
    @Id
    @Column(name="id_country", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorC")
    @SequenceGenerator(name="IdGeneratorC", sequenceName="country_seq")
    private Long idCountry;
    @Column(name = "code", nullable=false, length = 2)
    private String code;
    @Column(name = "name", nullable=false, length = 50)
    private String name;


    public CountryEntity() {
    }

    public CountryEntity(Long idCountry, String code, String name) {
        this.idCountry = idCountry;
        this.code = code;
        this.name = name;
    }

    public Long getIdCountry() {
        return this.idCountry;
    }

    public void setIdCountry(Long idCountry) {
        this.idCountry = idCountry;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity idCountry(Long idCountry) {
        this.idCountry = idCountry;
        return this;
    }

    public CountryEntity code(String code) {
        this.code = code;
        return this;
    }

    public CountryEntity name(String name) {
        this.name = name;
        return this;
    }
}
