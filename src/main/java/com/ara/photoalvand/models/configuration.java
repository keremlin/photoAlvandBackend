package com.ara.photoalvand.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String configKey;
    private String configValue;
    private String configLabel;
    private int type;
}
