package com.ara.photoalvand.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ara.photoalvand.models.configuration;
import com.ara.photoalvand.viewModels.vmConfigurations;
import com.ara.photoalvand.services.configurationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/configuration")
public class configurationController {
    @Autowired
    private configurationService service;
    @GetMapping(value="/getConfig/{configKey}")
    public String getMethodName(@PathVariable String configKey) {
        return service.getConfig(configKey);
    }
    @PostMapping("/updateConfig")
    private configuration updateConfig(@RequestBody configuration configuration){
        return service.updateConfig(configuration);
    }
    @PostMapping("/setConfig")
    private configuration setConfig(@RequestBody configuration configuration){
        return service.setConfig(configuration);
    }
    @PostMapping("/getAllConfiguratin")
    private Iterable<configuration> getAllConfiguration(){     
        return service.getAllConfigurations();
    }
    
    @PutMapping("/putConfiguration")
    private boolean putConfiguration(@RequestBody vmConfigurations configurations){
        return service.putConfigurtions(configurations);
    }
    
}
