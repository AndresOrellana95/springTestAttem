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
@Table(name = "binnacle")
public class BinnacleEntity {
    @Id
    @Column(name="id_binnacle", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="IdGeneratorB")
    @SequenceGenerator(name="IdGeneratorB", sequenceName="binnacle_seq")
    private Long idBinnacle;
    @Column(name = "action", nullable=false, length = 50)
    private String action;
    @Column(name = "execution", nullable=false)
    private Date execution;
    @ManyToOne
    @JoinColumn(name="id_user", nullable = false)
    private UserEntity idUser;


    public BinnacleEntity() {
    }

    public BinnacleEntity(Long idBinnacle, String action, Date execution, UserEntity idUser) {
        this.idBinnacle = idBinnacle;
        this.action = action;
        this.execution = execution;
        this.idUser = idUser;
    }

    public Long getIdBinnacle() {
        return this.idBinnacle;
    }

    public void setIdBinnacle(Long idBinnacle) {
        this.idBinnacle = idBinnacle;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public BinnacleEntity idBinnacle(Long idBinnacle) {
        this.idBinnacle = idBinnacle;
        return this;
    }

    public BinnacleEntity action(String action) {
        this.action = action;
        return this;
    }

    public BinnacleEntity execution(Date execution) {
        this.execution = execution;
        return this;
    }

    public BinnacleEntity idUser(UserEntity idUser) {
        this.idUser = idUser;
        return this;
    }
}
