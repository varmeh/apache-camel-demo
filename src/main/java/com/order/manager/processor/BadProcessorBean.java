package com.order.manager.processor;

import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Component
public class BadProcessorBean {
    public void printOrderInformation(@Header("CamelFileName") String fileName) {
        System.out.println("Filename - Bad Order: " + fileName);
    }
}
