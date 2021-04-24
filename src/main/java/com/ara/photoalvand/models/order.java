package com.ara.photoalvand.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.Date;

import lombok.Data;

@Data
@Entity
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createDate;

    @ManyToOne
    private User owner;

    @ManyToMany
    private Collection<file> files;
    
    private int payment;

    private boolean isPayed;

    private String bankTransactionNumber;

    private boolean isFinished;
    
}
