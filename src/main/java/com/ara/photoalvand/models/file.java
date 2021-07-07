package com.ara.photoalvand.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class file {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @NotNull
    private String physicalPath;
    @NotNull
    private String title;

    private String description;

    @ManyToOne
    private album album;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<category> categories;

    @ManyToOne
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<orderCase> orderCases;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createDate;

    private String photographer;
    
    private int price;

    private boolean isAccepted;
    private boolean isReviewed;
    private boolean isEnabled;


    
}
