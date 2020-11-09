package com.demo.donations.model.objects;

public class OperationInterfaceA extends OperationsInterface {
    private String name;
    private String lastname;
    private String email;
    private String document;

    public OperationInterfaceA() {
    }

    public OperationInterfaceA(String name, String lastname, String email, String document) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.document = document;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public OperationInterfaceA name(String name) {
        this.name = name;
        return this;
    }

    public OperationInterfaceA lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public OperationInterfaceA email(String email) {
        this.email = email;
        return this;
    }

    public OperationInterfaceA document(String document) {
        this.document = document;
        return this;
    }
    
}