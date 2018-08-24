package com.order.manager.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class WarehouseProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println("Order in Warehouse: " + exchange.getIn().getHeader("CamelFileName"));
    }
}
