package com.ara.photoalvand;

import static org.junit.jupiter.api.Assertions.*;

import com.ara.photoalvand.services.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilTests {
    
    @Test
    void checkRandomNumberCreation() {
        assertTrue(util.RandomNumberBetween(10, 20) >= 10);
        assertTrue(util.RandomNumberBetween(100, 20000) >= 100);
        assertEquals(0, util.RandomNumberBetween(20, 10));
        assertEquals(0, util.RandomNumberBetween(-2, 10));
        assertEquals(0, util.RandomNumberBetween(2, -10));
        assertEquals(0, util.RandomNumberBetween(0, 0));
        assertTrue(util.RandomNumberBetween(0, 10) >= 0);
        assertEquals(1, util.RandomNumberBetween(1, 1));
        assertEquals(100, util.RandomNumberBetween(100, 100));
    }
}
