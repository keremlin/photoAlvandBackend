package com.ara.photoalvand.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

<<<<<<< HEAD
import org.springframework.context.annotation.Lazy;

=======
>>>>>>> 833e0bae060ea661a67ae82e116d1132d8cca02a
import lombok.Data;

@Data
@Entity
public class category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String name;

    private String description;

    @ManyToMany
    @JsonIgnore
    private Collection<file> files;
}
