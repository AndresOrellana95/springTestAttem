package com.demo.donations.model.objects;

public class Companies {
    
    private Long id;
    private String name;
    private Long country;

    public Companies() {
    }

    public Companies(Long id, String name, Long country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountry() {
        return this.country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Companies id(Long id) {
        this.id = id;
        return this;
    }

    public Companies name(String name) {
        this.name = name;
        return this;
    }

    public Companies country(Long country) {
        this.country = country;
        return this;
    }
}
