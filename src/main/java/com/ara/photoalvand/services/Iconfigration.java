package com.ara.photoalvand.services;

import com.ara.photoalvand.models.configuration;

public interface Iconfigration {
    public String getConfig(String key);
    public configuration setConfig(configuration configuration);
    public configuration updateConfig(configuration configuration);
    public Iterable<configuration> getAllConfigurations();
}
