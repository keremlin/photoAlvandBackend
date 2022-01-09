package com.ara.photoalvand.services;

import java.util.List;

import com.ara.photoalvand.models.configuration;
import com.ara.photoalvand.repository.configurationRepository;
import com.ara.photoalvand.viewModels.vmConfigurations;

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
        return (
                configuration.getConfigKey() != null && configuration.getConfigKey() != ""
                && repo.findByConfigKey(configuration.getConfigKey()).isEmpty() 
                ? repo.save(configuration) 
                : null);
    }
    public configuration updateConfig(configuration configuration) {
        var optional=repo.findByConfigKey(configuration.getConfigKey());
        var conf=optional.get();
        conf.setConfigValue(configuration.getConfigValue());
        return (optional.isPresent() ? repo.save(conf) : null);
    }
    public List<configuration> getAllConfigurations(){
        return (repo.findAll());
    }
    public boolean deleteConfiguration(String configKey){
        if(configKey!=null && !configKey.equals("")){
            try{
                repo.delete(repo.findByConfigKey(configKey).orElse(null));
            }catch(Exception ex){return false;}
         return true;
        }
        else{return false;}    
    }
    public boolean putConfigurtions(vmConfigurations configs){
        for(configuration item : configs.configurations){
            if(item.getConfigValue()!=null && item.getConfigKey()!=null)
                updateConfig(item);
        }
        return true;
    }
}
