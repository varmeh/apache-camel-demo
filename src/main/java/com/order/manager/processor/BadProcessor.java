package com.order.manager.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class BadProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println("Bad Order: " + exchange.getIn().getHeader("CamelFileName"));
    }
}
