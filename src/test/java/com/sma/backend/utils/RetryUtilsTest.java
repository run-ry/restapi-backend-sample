package com.sma.backend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Author: Mak Sophea
 * Date: 08/17/2021
 */
public class RetryUtilsTest {

    public static int value = 0;

    public static void main(String[] args) throws Exception {
        RetryUtilsTest obj = new RetryUtilsTest();
        String result = (String) RetryUtils.withRetryObject(5, 6000, obj::test);

        System.out.println(result);
    }

    @Test
    public void testRetry() throws Exception {
        String result = (String) RetryUtils.withRetryObject(5, 6000, this::test);
        Assertions.assertEquals("Value", result);
    }

    public String test() throws Exception {
        if (value < 2) {
            value++;
            throw new Exception("sth wrong");
        }
        return "Value";
    }
}
