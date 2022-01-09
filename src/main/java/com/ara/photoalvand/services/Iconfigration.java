package com.ara.photoalvand.services;

import com.ara.photoalvand.models.configuration;
import com.ara.photoalvand.viewModels.vmConfigurations;

public interface Iconfigration {
    public String getConfig(String key);
    public configuration setConfig(configuration configuration);
    public configuration updateConfig(configuration configuration);
    public Iterable<configuration> getAllConfigurations();
    public boolean deleteConfiguration(String configKey);
    public boolean putConfigurtions(vmConfigurations configs);
}
