package com.order.manager.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class TxtProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        System.out.println("Processing Txt order: " + exchange.getIn().getHeader("CamelFileName"));
    }
}
