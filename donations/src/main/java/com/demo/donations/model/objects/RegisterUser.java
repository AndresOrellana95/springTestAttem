package com.demo.donations.model.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(
    ignoreUnknown = true
)
public class RegisterUser {
    private String document;
    private String names;
    private String password;
    private String lastnames;
    private String email;
    

    public RegisterUser() {
    }

    public RegisterUser(String document, String names, String password, String lastnames, String email) {
        this.document = document;
        this.names = names;
        this.password = password;
        this.lastnames = lastnames;
        this.email = email;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastnames() {
        return this.lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RegisterUser document(String document) {
        this.document = document;
        return this;
    }

    public RegisterUser names(String names) {
        this.names = names;
        return this;
    }

    public RegisterUser password(String password) {
        this.password = password;
        return this;
    }

    public RegisterUser lastnames(String lastnames) {
        this.lastnames = lastnames;
        return this;
    }

    public RegisterUser email(String email) {
        this.email = email;
        return this;
    }
   
}
