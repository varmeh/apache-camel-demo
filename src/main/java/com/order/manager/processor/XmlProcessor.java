package com.order.manager.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class XmlProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println("Processing Xml order: " + exchange.getIn().getHeader("CamelFileName"));
    }
}
