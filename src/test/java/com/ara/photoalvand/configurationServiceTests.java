package com.ara.photoalvand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import com.ara.photoalvand.models.configuration;
import com.ara.photoalvand.services.configurationService;
import com.ara.photoalvand.viewModels.vmConfigurations;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;





@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class configurationServiceTests {
    @Autowired
    private configurationService service;

    @Test
    @Order(1)
    void setConfig() {
        
        assertTrue(service.setConfig(
            configuration
            .builder()
            .configKey("configKey")
            .configLabel("configLabel")
            .configValue("configValue")
            .build()
            )!=null
            );
    }
    @Test
    @Order(2)
    void setConfigNull(){
        assertTrue(service.setConfig(
            configuration
            .builder()
            .configKey("")
            .configLabel("configLabel")
            .configValue("configValue")
            .build()
            )==null
            );
    }
    @Test
    @Order(3)
    void listTest(){
        List<configuration> list=service.getAllConfigurations();
        assertTrue(
            list != null
            && list.size()>0
            && list.get(list.size()-1).getId()>0
            );
    }
    @Test
    @Order(4)
    void getTest(){
        String testCase=service.getConfig("configKey");
        assertTrue(
            testCase!=null
            && testCase.equals("configValue")
        );
    }
    @Test
    @Order(5)
    void putTest(){
        List<configuration> list=new ArrayList<>();
                                    
        list.add(
                                        configuration.builder()
                                        .configKey("configKey")
                                        .configLabel("configLabel")
                                        .configValue("configValue12")
                                        .build()
        );
        list.add(
            configuration.builder()
            .configKey("configKey")
            .configLabel("configLabel")
            .configValue("configValue13")
            .build()
        );
        service.putConfigurtions(
                vmConfigurations.builder()
                .configurations(list).build()
            );

        String testCase=service.getConfig("configKey");
        assertTrue(
            testCase!=null
            && testCase.equals("configValue13")
        );
            
    }
    @Test
    @Order(6)
    void deleteTest() {
        assertFalse(service.deleteConfiguration(""));
        assertTrue(service.deleteConfiguration("configKey"));
        assertFalse(service.deleteConfiguration("configKey"));
    }
    

}
