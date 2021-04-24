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

    @ManyToMany
    private Collection<category> categories;

    @ManyToOne
    private User owner;

    @ManyToMany
    private Collection<order> orders;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createDate;

    private String photographer;

    private boolean isAccepted;
    private boolean isReviewed;
    private boolean isEnabled;


    
}
