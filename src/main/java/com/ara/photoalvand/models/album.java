package com.ara.photoalvand.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import java.util.Collection;

import lombok.Data;

@Data
@Entity
public class album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;
    
    private String description;
    
    @ManyToMany(mappedBy="albums")
    private Collection<category> categories;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date createDate;
    
    @OneToMany
    private Collection<file> files;


}
