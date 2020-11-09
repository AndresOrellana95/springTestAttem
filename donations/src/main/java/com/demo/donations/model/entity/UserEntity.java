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
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name="id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorU")
    @SequenceGenerator(name="IdGeneratorU", sequenceName="user_seq")
    private Long idUser;
    @Column(name = "document", nullable=false, length = 13)
    private String document;
    @Column(name = "name", nullable=false, length = 100)
    private String name;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name = "lastname", nullable=false, length = 100)
    private String lastname;
    @Column(name = "email", nullable=false, length = 100)
    private String email;
    @ManyToOne
    @JoinColumn(name="id_level", nullable=false)
    private UserLevelEntity userLevel;

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getDocument() {
        return this.document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserLevelEntity getUserLevel() {
        return this.userLevel;
    }

    public void setUserLevel(UserLevelEntity userLevel) {
        this.userLevel = userLevel;
    }

    public UserEntity idUser(Long idUser) {
        this.idUser = idUser;
        return this;
    }

    public UserEntity document(String document) {
        this.document = document;
        return this;
    }

    public UserEntity name(String name) {
        this.name = name;
        return this;
    }

    public UserEntity password(String password) {
        this.password = password;
        return this;
    }

    public UserEntity lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserEntity email(String email) {
        this.email = email;
        return this;
    }

    public UserEntity userLevel(UserLevelEntity userLevel) {
        this.userLevel = userLevel;
        return this;
    }
}
