package com.demo.donations.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_level")
public class UserLevelEntity {

    @Id
    @Column(name="id_level", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorUL")
    @SequenceGenerator(name="IdGeneratorUL", sequenceName="user_level_seq")
    private Long idLevel;
    @Column(name = "code", nullable=false, length = 2)
    private String code;
    @Column(name = "name", nullable=false, length = 50)
    private String name;

    public Long getIdLevel() {
        return this.idLevel;
    }

    public void setIdLevel(Long idLevel) {
        this.idLevel = idLevel;
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

    public UserLevelEntity idLevel(Long idLevel) {
        this.idLevel = idLevel;
        return this;
    }

    public UserLevelEntity code(String code) {
        this.code = code;
        return this;
    }

    public UserLevelEntity name(String name) {
        this.name = name;
        return this;
    }
}
