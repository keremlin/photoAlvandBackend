package com.ara.photoalvand.services;

import com.ara.photoalvand.models.configuration;
import com.ara.photoalvand.repository.configurationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class configurationService implements Iconfigration {
    @Autowired
    private configurationRepository repo;

    public  String getConfig(String configKey){
        return repo.findByConfigKey(configKey).orElseThrow(
            IllegalArgumentException::new).getConfigValue().toString();
    }
    
    public configuration setConfig(configuration configuration) {
        return (configuration.getConfigKey() != null && configuration.getConfigKey() != ""
                && repo.findByConfigKey(configuration.getConfigKey()).isEmpty() ? repo.save(configuration) : null);
    }
    public configuration updateConfig(configuration configuration) {
        var optional=repo.findByConfigKey(configuration.getConfigKey());
        var conf=optional.get();
        conf.setConfigValue(configuration.getConfigValue());
        return (optional.isPresent() ? repo.save(conf) : null);
    }
    public Iterable<configuration> getAllConfigurations(){
        return (repo.findAll());
    }
}
