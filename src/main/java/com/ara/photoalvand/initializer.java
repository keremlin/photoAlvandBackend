package com.ara.photoalvand;

import com.ara.photoalvand.models.configuration;
import com.ara.photoalvand.services.configurationService;
import com.ara.photoalvand.services.userService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class initializer {
    private static final Logger log = LoggerFactory.getLogger(initializer.class);
    @Autowired
	private configurationService configurationS;
    @Autowired
    private userService UserService;
    
    @Bean
    public void init(){
        log.info("Start init...");
        log.info(configurationS.isConfigurationsAvailable()+" -09");
        log.info( "Init Users and Roles ...");
        UserService.initRoles();
        UserService.initAdminUser();
        if(configurationS.isConfigurationsAvailable()==false)
        {
            log.info("Config slider ....");
            configurationS.setConfig(new configuration(0, "indexFirstSliderBigText","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexFirstSliderSmallText","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexFirstSliderPictureId","configValue", "configLabel", 0));

            configurationS.setConfig(new configuration(0, "indexSecondSliderBigText","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexSecondSliderSmallText","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexSecondSliderPictureId","configValue", "configLabel", 0));

            configurationS.setConfig(new configuration(0, "indexThirdSliderBigText","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexThirdSliderSmallText","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexThirdSliderPictureId","configValue", "configLabel", 0));

            log.info("Config group number");
            configurationS.setConfig(new configuration(0, "indexFirstGroupNumber","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexSecondGroupNumber","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexThirdGroupNumber","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexFourthGroupNumber","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexfifthGroupNumber","configValue", "configLabel", 0));

            log.info("Config rows");
            configurationS.setConfig(new configuration(0, "indexRowNumber","configValue", "configLabel", 0));
            configurationS.setConfig(new configuration(0, "indexColumnNumber","configValue", "configLabel", 0));
        }
    }
}
