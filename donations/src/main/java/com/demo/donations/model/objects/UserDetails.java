package com.demo.donations.model.objects;

public class UserDetails {
    
    private String name;
    private String email;
    private Long level;

    public UserDetails() {
    }

    public UserDetails(String name, String email, Long level) {
        this.name = name;
        this.email = email;
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLevel() {
        return this.level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public UserDetails name(String name) {
        this.name = name;
        return this;
    }

    public UserDetails email(String email) {
        this.email = email;
        return this;
    }

    public UserDetails level(Long level) {
        this.level = level;
        return this;
    }
}
