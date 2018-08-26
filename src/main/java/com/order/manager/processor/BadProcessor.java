package com.order.manager.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class BadProcessor implements Processor {

    // Handler annotation is required only when there are 2 methods of similar signature.
    // In such case, this annotation avoids ambiguity & defines default method for invocation by BeanProcessor
    @Handler
    public void process(Exchange exchange) throws Exception {
        System.out.println("Bad Order: " + exchange.getIn().getHeader("CamelFileName"));
    }
}
