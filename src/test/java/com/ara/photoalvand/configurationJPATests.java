package com.ara.photoalvand;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import com.ara.photoalvand.models.configuration;
import com.ara.photoalvand.repository.configurationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;





@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class configurationJPATests {
    @Autowired
    private configurationRepository repo;

    @Test
    @Order(1)
    @Rollback(value=false)
    void saveTest() {
        configuration testCase=repo.save(configuration.builder().configKey("test").configLabel("configLabel").configValue("configValue")
                .type(1).build());
        Assertions.assertThat(testCase.getId()).isGreaterThan(0);
    }
    @Test
    @Order(2)
    void getTest(){
        assertTrue(repo.findByConfigKey("test").isPresent());
    }
    @Test
    @Order(3)
    void listTest(){
        // assertTrue(repo.findAll()>0);
    }
    @Test
    @Order(4)
    @Rollback(value=false)
    void delTest(){
        Optional<configuration> testCase=repo.findByConfigKey("test");
        if(testCase.isPresent()){
            repo.delete(testCase.get());
            Optional<configuration> testResult=repo.findByConfigKey("test");
            assertTrue(!testResult.isPresent());
        }else{
            assertTrue(false);
        }
            

        assertTrue(true);
    }
}
