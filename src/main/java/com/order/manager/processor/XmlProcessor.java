package com.order.manager.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Header;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class XmlProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println("Processing Xml order: " + exchange.getIn().getHeader("CamelFileName"));
    }

    @Handler
    public void printOrderInformation(@Header("CamelFileName") String fileName) {
        System.out.println("Filename - Xml order: " + fileName);
    }
}
